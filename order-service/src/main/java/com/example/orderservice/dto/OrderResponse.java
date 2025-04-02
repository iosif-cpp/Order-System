package com.example.orderservice.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private Long userId;
    private String product;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}