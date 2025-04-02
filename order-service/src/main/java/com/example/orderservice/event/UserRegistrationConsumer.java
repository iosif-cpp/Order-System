package com.example.orderservice.event;

import com.example.orderservice.model.UserRef;
import com.example.orderservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegistrationConsumer {
    private final UserService userService;

    @KafkaListener(
            topics = "user-registration-topic",
            containerFactory = "userRegistrationKafkaListenerContainerFactory"
    )
    public void handleUserRegistration(UserRegisteredEvent event) {
        UserRef user = UserRef.builder()
                .id(event.getUserId())
                .email(event.getEmail())
                .role(UserRef.Role.valueOf(event.getRole()))
                .build();
        userService.createUserReference(user);
    }
}
