package org.example.dto;

import java.sql.Timestamp;

public record UserResponseDto(
        Long id,
        String name,
        String email,
        int age,
        Timestamp createdAt
) {
}
