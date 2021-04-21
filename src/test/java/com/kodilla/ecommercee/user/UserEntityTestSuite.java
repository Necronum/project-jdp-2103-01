package com.kodilla.ecommercee.user;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserEntityTestSuite {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartRepository;

    @Test
    public void shouldSaveUserInRepository() {
        User user = getFakeUser();
        userRepository.save(user);

        Long id = user.getId();
        Optional<User> userFromRepository = userRepository.findById(id);

        assertTrue(userFromRepository.isPresent());
    }

    @Test
    public void shouldUpdateUserInRepository() {
        User user = getFakeUser();
        userRepository.save(user);

        Long id = user.getId();
        userRepository.findById(id)
                .ifPresent(foundUser -> {
                    foundUser.setActiveStatus(false);
                    userRepository.save(foundUser);
                });
        User userFromRepository = userRepository.findById(id).get();

        assertFalse(userFromRepository.isActiveStatus());
    }

    @Test
    public void shouldRemoveUserAndLeaveOrderInRepository() {
        User user = getFakeUser();
        Order order = new Order();
        order.addUser(user);
        orderRepository.save(order);

        order.setUser(null);
        orderRepository.save(order);
        Long id = user.getId();
        userRepository.deleteById(id);

        assertEquals(0, userRepository.count());
        assertEquals(1, orderRepository.count());
    }

    @Test
    public void shouldRemoveUserAndLeaveCartInRepository() {
        User user = getFakeUser();
        Cart cart = new Cart();
        user.setCart(cart);
        userRepository.save(user);

        Long id = user.getId();
        userRepository.deleteById(id);

        assertEquals(1, cartRepository.count());
        assertEquals(0, userRepository.count());
    }

    private User getFakeUser() {
        return new User("Robert", "Maklowicz", "rob@example.com",
                "600581879","Krakow", "Szewska 1/1", "30-000", true, 1);
    }

}
