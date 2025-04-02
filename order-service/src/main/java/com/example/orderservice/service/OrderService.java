package com.example.orderservice.service;


import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.UserRef;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.repository.UserRefRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRefRepository userRefRepository;

    public OrderResponse createOrder(OrderRequest request) {
        UserRef user = userRefRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = Order.builder()
                .user(user)
                .product(request.getProduct())
                .amount(request.getAmount())
                .build();

        Order savedOrder = orderRepository.save(order);
        return convertToResponse(savedOrder);
    }

    public List<OrderResponse> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(this::convertToResponse)
                .toList();
    }

    private OrderResponse convertToResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .product(order.getProduct())
                .amount(order.getAmount())
                .createdAt(order.getCreatedAt())
                .build();
    }
}