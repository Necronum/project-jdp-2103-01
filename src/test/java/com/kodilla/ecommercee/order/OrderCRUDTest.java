package com.kodilla.ecommercee.order;

import com.kodilla.ecommercee.OrderStatus;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderCRUDTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void shouldSaveOrderInRepository() {
        User user = new User();
        Order order = new Order(user, new Cart(), OrderStatus.PAID, LocalDateTime.now(), LocalDateTime.now());

        orderRepository.save(order);

        Long id = order.getId();
        Optional<Order> orderFromRepository = orderRepository.findById(id);

        assertTrue(orderFromRepository.isPresent());
    }

    @Test
    public void shouldRemoveOrderFromRepository() {
        Order order = new Order(new User(), new Cart(), OrderStatus.PAID, LocalDateTime.now(), LocalDateTime.now());
        orderRepository.save(order);

        orderRepository.delete(order);

        assertEquals(0, orderRepository.count());
    }

    @Test
    public void shouldUpdateOrderInRepository() {
        Order order = new Order(new User(), new Cart(), OrderStatus.PAID, LocalDateTime.now(), LocalDateTime.now());
        orderRepository.save(order);

        Long id = order.getId();
        Order orderFromRepository = orderRepository.findById(id).get();
        orderFromRepository.setStatus(OrderStatus.DELIVERED);

        assertEquals("DELIVERED", orderFromRepository.getStatus().toString());
    }

}
