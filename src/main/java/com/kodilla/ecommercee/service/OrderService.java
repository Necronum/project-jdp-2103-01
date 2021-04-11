package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<?> getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::mapToOrderDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public void addOrder(OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        orderRepository.save(order);
    }

    public ResponseEntity<?> updateOrder(Long id, OrderDto orderDto) {
        return orderRepository.findById(id)
                .map(order -> {
                    Order updatedOrder = updateOrderFields(orderDto, order);
                    orderRepository.save(updatedOrder);
                    return ResponseEntity.accepted().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private Order updateOrderFields(OrderDto orderDto, Order order) {
        if (orderDto.getUser() != null) {
            order.setUser(userRepository.findById(orderDto.getUser().getId()).get());
        }
        if (orderDto.getStatus() != null) {
            order.setStatus(orderDto.getStatus());
        }
        return order;
    }
}
