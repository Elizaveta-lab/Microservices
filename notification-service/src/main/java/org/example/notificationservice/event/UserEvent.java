package org.example.notificationservice.event;

public record UserEvent(
        String operation,
        String email,
        Long userId
) {
}