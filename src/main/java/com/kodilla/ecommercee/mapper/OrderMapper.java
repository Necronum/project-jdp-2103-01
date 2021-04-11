package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.OrderStatus;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderMapper {

    UserRepository userRepository;
    CartRepository cartRepository;

    public OrderDto mapToOrderDto(Order order) {
        return OrderDto.builder()
                        .id(order.getId())
                        .user(order.getUser())
                        .cart(order.getCart())
                        .status(order.getStatus())
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
                .user(userRepository.findById(orderDto.getUser().getId())
                        .orElseThrow(() -> resourceNotFound("User not found")))
                .cart(cartRepository.findById(orderDto.getCart().getId())
                        .orElseThrow(() -> resourceNotFound("Cart not found")))
                .status(OrderStatus.parseString(orderDto.getStatus().toString())
                        .orElseThrow(() -> resourceNotFound("Unknown status")))
                .build();
    }

    private ResponseStatusException resourceNotFound(String s) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, s);
    }

}
