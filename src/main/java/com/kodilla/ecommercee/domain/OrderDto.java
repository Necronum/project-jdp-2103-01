package com.kodilla.ecommercee.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderDto {
    Long id;
    Long userId;
    Long cartId;
    String status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
