package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Value
@Builder
public class OrderDto {
    Long id;
    User user;
    Cart cart;
    OrderStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
