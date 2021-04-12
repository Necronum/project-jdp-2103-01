package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.OrderStatus;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.exception.ResourceNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderMapper {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public OrderDto mapToOrderDto(Order order) {
        return OrderDto.builder()
                        .id(order.getId())
                        .userId(order.getUser().getId())
                        .cartId(order.getCart().getId())
                        .status(order.getStatus().toString())
                        .createdAt(order.getCreatedAt())
                        .updatedAt(order.getUpdatedAt())
                        .build();
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orders) {
        return orders
                .stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }

    public Order mapToOrder(OrderDto orderDto) {
        return Order.builder()
                .user(userRepository.findById(orderDto.getUserId())
                        .orElseThrow(() -> new ResourceNotFoundException("User with id: " + orderDto.getUserId() + " not found")))
                .cart(cartRepository.findById(orderDto.getCartId())
                        .orElseThrow(() -> new ResourceNotFoundException("Cart with id: " + orderDto.getCartId() + " not found")))
                .status(OrderStatus.parseString(orderDto.getStatus())
                        .orElseThrow(() -> new IllegalArgumentException("Unknown order status")))
                .build();
    }

}
