package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderDto {
    Long id;
    Long userId;
    Long cartId;
    OrderStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

//    @Value
//    @Builder
//    public static class RichOrderDto {
//        Long id;
//        User user;
//        Cart cart;
//        OrderStatus status;
//        LocalDateTime createdAt;
//        LocalDateTime updatedAt;
//    }

}
