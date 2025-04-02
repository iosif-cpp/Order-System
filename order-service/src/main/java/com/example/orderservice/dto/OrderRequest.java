package com.example.orderservice.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderRequest {
    private Long userId;
    private String product;
    private BigDecimal amount;
}
