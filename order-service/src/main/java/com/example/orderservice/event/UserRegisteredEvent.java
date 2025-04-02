package com.example.orderservice.event;

import lombok.Data;

@Data
public class UserRegisteredEvent {
    private Long userId;
    private String email;
    private String role;
}