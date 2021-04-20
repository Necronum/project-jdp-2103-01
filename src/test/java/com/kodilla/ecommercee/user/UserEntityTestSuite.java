package com.kodilla.ecommercee.user;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserEntityTestSuite {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;


    @Test
    public void shouldSaveUserInRepository() {
//        User user = User.builder()
//                .username("yolo")
//                .firstname("Robert")
//                .lastname("Maklowicz")
//                .email("rob@example.com")
//                .build();

//        userRepository.save(user);
//
//        Long id = user.getId();
//        Optional<User> userFromRepository = userRepository.findById(id);

//        assertTrue(userFromRepository.isPresent());
    }


    @Test
    public void shouldShouldRemoveUserAndOrderFromRepository() {
//        Order order = Order.builder()
//                .build();



//        userRepository.save();

//        userRepository.deleteById(u);

//        assertEquals(1, userRepository.count());
//        assertEquals(0, orderRepository.count());
    }
}
