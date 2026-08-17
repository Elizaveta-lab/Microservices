package org.example.notificationservice.config;

import org.example.notificationservice.notification.NotificationType;
import org.example.notificationservice.notification.strategy.AccountCreatedStrategy;
import org.example.notificationservice.notification.strategy.AccountDeletedStrategy;
import org.example.notificationservice.notification.strategy.NotificationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class NotificationConfig {
    @Bean
    public Map<NotificationType, NotificationStrategy> notificationStrategies(
            AccountCreatedStrategy created,
            AccountDeletedStrategy deleted) {

        return Map.of(
                NotificationType.CREATE, created,
                NotificationType.DELETE, deleted
        );
    }
}
