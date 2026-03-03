package com.anish.telegram.service.impl;

import com.anish.telegram.config.properties.TelegramProperties;
import com.anish.telegram.dto.NotificationRequestDto;
import com.anish.telegram.service.TelegramService;
import com.anish.telegram.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@Slf4j
@RequiredArgsConstructor
public class TelegramServiceImpl implements TelegramService {

    private final ObjectMapper objectMapper;

    private final WebClient webclient;

    private final TelegramProperties telegramProperties;


    @Override
    @Async("asyncExecutor")
    public void sendTelegramNotification(NotificationRequestDto request) {
        ObjectNode payload = buildNotificationRequest(request);
        sendMessage(payload);
    }

    private void sendMessage(ObjectNode payload) {
        URI uri = UriComponentsBuilder
                .fromUriString(telegramProperties.botBasePath())
                .path("/sendMessage")
                .build()
                .toUri();
        webclient.post()
                .uri(uri)
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(body -> log.info("Response: {}", body))
                .block();
    }

    private ObjectNode buildNotificationRequest(NotificationRequestDto request) {
        String messageFormatter = "<b>%s</b>\n\n<i>%s</i>\n\n%s";
        String text = messageFormatter.formatted(request.title(),
                request.subtitle(), request.content());

        ObjectNode root = objectMapper.createObjectNode();
        root.put(Constants.TelegramConstants.CHAT_ID, request.chatId());
        root.put(Constants.CommonConstants.TEXT, text);

        ObjectNode replyMarkup = objectMapper.createObjectNode();
        ArrayNode inlineKeyboard = objectMapper.createArrayNode();
        ArrayNode buttonRow = objectMapper.createArrayNode();

        ObjectNode button = objectMapper.createObjectNode();
        button.put(Constants.CommonConstants.TEXT, request.buttonTitle());
        button.put(Constants.CommonConstants.URL, request.url());

        buttonRow.add(button);
        inlineKeyboard.add(buttonRow);
        replyMarkup.set(Constants.TelegramConstants.INLINE_KEYBOARD, inlineKeyboard);
        root.set(Constants.TelegramConstants.REPLY_MARKUP, replyMarkup);
        return root;
    }
}
