package org.example.service;

import org.example.dto.CreateUserRequest;
import org.example.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(CreateUserRequest request);

    UserResponseDto getUserById(Long id);

    UserResponseDto updateUser(Long id, CreateUserRequest request);

    void deleteUser(Long id);

    List<UserResponseDto> getAllUsers();
}
