package com.anish.telegram.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "telegram")
public record TelegramProperties(
        String baseUrl,
        String botToken
) {

    public String botBasePath() {
        return String.format("%s/bot%s", baseUrl, botToken);
    }
}
