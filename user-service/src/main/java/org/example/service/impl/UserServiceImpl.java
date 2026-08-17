package org.example.service.impl;

import org.example.dto.CreateUserRequest;
import org.example.dto.UserResponseDto;
import org.example.entity.User;
import org.example.event.UserEvent;
import org.example.kafka.UserEventProducer;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserEventProducer eventProducer;

    @Override
    @Transactional
    public UserResponseDto createUser(CreateUserRequest request) {
        User user = new User(request.getName(), request.getEmail(), request.getAge());
        User saved = userRepository.save(user);

        eventProducer.send(UserEvent.create(saved.getEmail(), saved.getId()));

        return mapToDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return mapToDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long id, CreateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());

        User updated = userRepository.save(user);
        return mapToDto(updated);
    }

    @Override
    @Transactional
    @CircuitBreaker(name = "deleteUser", fallbackMethod = "deleteFallback")
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        String email = user.getEmail();
        userRepository.deleteById(id);

        eventProducer.send(UserEvent.delete(email, id));
    }

    public void deleteFallback(Long id, Throwable t) {
        log.warn("Circuit Breaker сработал для удаления пользователя {}. Причина: {}", id, t.getMessage());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    private UserResponseDto mapToDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getCreatedAt()
        );
    }
}
