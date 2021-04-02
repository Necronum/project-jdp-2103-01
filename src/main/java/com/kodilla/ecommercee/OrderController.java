package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("v1/order")
public class OrderController {

    private final OrderMapper orderMapper;
    private final GenericEntityRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders() {
        List<GenericEntity> orders = repository.findAll();
        return orderMapper.mapToOrderDtoList(orders);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrderById(@PathVariable Long id) {
        return repository.findById(id)
                .map(orderMapper::mapToOrderDto)
                .orElseThrow(() -> new IllegalStateException("Order not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrder(@RequestBody OrderDto orderDto) {
        GenericEntity order = orderMapper.mapToOrder(orderDto);
        repository.save(order);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        repository.findById(id)
                .ifPresent(order -> {
                    OrderDto.updateFields(orderDto, order);
                    repository.save(order);
                });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeOrderById(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
