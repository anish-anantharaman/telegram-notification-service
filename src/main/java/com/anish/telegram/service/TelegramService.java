package com.anish.telegram.service;

import com.anish.telegram.dto.NotificationRequestDto;

public interface TelegramService {

    void sendTelegramNotification(NotificationRequestDto request);
}
