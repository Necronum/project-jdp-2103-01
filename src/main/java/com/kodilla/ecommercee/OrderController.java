package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.OrderDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("v1/order")
public class OrderController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders() {
        return Collections.emptyList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrderById(@PathVariable Long id) {
        return new OrderDto(id, "Order");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrder(@RequestBody OrderDto orderDto) {
        log.info("Order saved in database");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        log.info("Order " + id + " updated");
        return orderDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeOrderById(@PathVariable Long id) {
        log.info("Order " + id + " removed from database");
    }

}
