package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.OrderStatus;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.exception.ResourceNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.mapToOrderDtoList(orders);
    }

    public OrderDto getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::mapToOrderDto)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id: " + id + " not found"));
    }

    public void addOrder(OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        orderRepository.save(order);
    }

    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        return orderRepository.findById(id)
                .map(order -> {
                    Order updatedOrder = updateOrderFields(orderDto, order);
                    orderRepository.save(updatedOrder);
                    return orderMapper.mapToOrderDto(updatedOrder);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Order with id: " + id + " not found"));
    }

    private Order updateOrderFields(OrderDto orderDto, Order order) {
        if (orderDto.getUserId() != null) {
            order.setUser(userRepository.findById(orderDto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User with id: " + orderDto.getUserId() + " not found")));
        }
        if (orderDto.getStatus() != null) {
            order.setStatus(OrderStatus.parseString(orderDto.getStatus())
                    .orElseThrow(() -> new IllegalArgumentException("Unknown order status")));
        }
        return order;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
