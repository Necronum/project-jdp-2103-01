package com.kodilla.ecommercee.order;

import com.kodilla.ecommercee.OrderStatus;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OrderCRUDTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Test
    public void shouldSaveOrderInRepository() {
        Order order = prepareOrder("Wine", "Red dry wine", 19.99);

        orderRepository.save(order);

        Long id = order.getId();
        Optional<Order> orderFromRepository = orderRepository.findById(id);

        assertTrue(orderFromRepository.isPresent());
    }

    @Test
    public void shouldRemoveOrderFromRepositoryButLeaveUser() {
        Order order = prepareOrder("Cheese", "Italian Gorgonzola", 9.99);
        orderRepository.save(order);

        orderRepository.delete(order);
        Long userId = order.getUser().getId();
        Optional<User> userFromRepository = userRepository.findById(userId);

        assertEquals(0, orderRepository.count());
        assertTrue(userFromRepository.isPresent());
    }

    @Test
    public void shouldRemoveOrderFromRepositoryAndCart() {
        Order order = prepareOrder("Cheese", "Italian Gorgonzola", 9.99);
        orderRepository.save(order);

        orderRepository.delete(order);
        Long cartId = order.getCart().getId();
        Optional<Cart> cartFromRepository = cartRepository.findById(cartId);

        assertEquals(0, orderRepository.count());
        assertFalse(cartFromRepository.isPresent());
    }

    @Test
    public void shouldUpdateOrderInRepository() {
        Order order = prepareOrder("Bread", "Home made bread", 4.99);
        orderRepository.save(order);

        Long id = order.getId();
        orderRepository.findById(id)
                .ifPresent(foundOrder -> {
                    foundOrder.setStatus(OrderStatus.DELIVERED);
                    orderRepository.save(foundOrder);
                });

        Order orderFromRepository = orderRepository.findById(id).get();

        assertEquals("DELIVERED", orderFromRepository.getStatus().toString());
    }

    private Order prepareOrder(String productName, String description, double price) {
        User client = new User("John", "Doe", "jdoe@example.com", null, "Warsaw", "Wymyślona 1/1", "00-000", true, 1);
        Cart cart = new Cart();
        Product product = new Product(productName, description, price);
        cart.setProducts(Arrays.asList(product));

        return Order.builder()
                .user(client)
                .cart(cart)
                .build();
    }

}
