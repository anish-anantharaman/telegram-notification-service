package com.anish.telegram.controller;

import com.anish.telegram.dto.ApiResponseDto;
import com.anish.telegram.dto.NotificationRequestDto;
import com.anish.telegram.service.TelegramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/notifications")
@RequiredArgsConstructor
public class TelegramController {

    private final TelegramService telegramService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sendTelegramNotification(@Valid @RequestBody NotificationRequestDto request) {
        telegramService.sendTelegramNotification(request);

        ApiResponseDto response = new ApiResponseDto(
                HttpStatus.ACCEPTED.value(),
                HttpStatus.ACCEPTED.getReasonPhrase(),
                "Notification queued successfully",
                Boolean.TRUE
        );
        return ResponseEntity.accepted().body(response);
    }
}
