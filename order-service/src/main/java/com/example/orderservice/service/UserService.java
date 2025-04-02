package com.example.orderservice.service;

import com.example.orderservice.model.UserRef;
import com.example.orderservice.repository.UserRefRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRefRepository userRefRepository;

    public void createUserReference(UserRef user) {
        if (!userRefRepository.existsById(user.getId())) {
            userRefRepository.save(user);
        }
    }

    public UserRef getUserReference(Long userId) {
        return userRefRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}