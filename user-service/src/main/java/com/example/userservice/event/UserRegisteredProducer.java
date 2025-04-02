package com.example.userservice.event;

import com.example.userservice.model.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserRegisteredProducer {
    private final KafkaTemplate<String, UserRegisteredEvent> kafkaTemplate;

    public UserRegisteredProducer(KafkaTemplate<String, UserRegisteredEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserRegisteredEvent(User user) {
        UserRegisteredEvent event = new UserRegisteredEvent(
                user.getId(),
                user.getEmail(),
                user.getRole().name()
        );

        kafkaTemplate.send("user-registration-topic", event)
                // Используем CompletableFuture вместо deprecated addCallback
                .whenComplete((result, throwable) -> {
                    if (throwable != null) {
                        System.err.println("Failed to send event: " + throwable.getMessage());
                    } else {
                        System.out.println("Event sent successfully: " + event);
                    }
                });
    }
}