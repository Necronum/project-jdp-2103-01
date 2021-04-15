package com.kodilla.ecommercee.cart;

import com.kodilla.ecommercee.domain.Cart;
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
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class CartEntityTestSuite {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindById() {
        //Given
        Cart cart = new Cart();

        //When
        cartRepository.save(cart);
        Long id = cart.getId();

        //Then
        Optional<Cart> cartOptional = cartRepository.findById(id);
        assertEquals(id, cartOptional.get().getId());

        //CleanUp
        cartRepository.deleteById(id);
    }

    @Test
    public void testDeleteCart() {
        //Given
        Cart cart = new Cart();

        //When
        cartRepository.save(cart);
        Long id = cart.getId();
        cartRepository.deleteById(id);

        //Then
        List<Cart> cartList = cartRepository.findAll();
        assertEquals(0, cartList.size());
    }

    @Test
    public void testUpdateCart() {
        //Given
        Product product = new Product("product 1", "test description", 1.1);
        Product product1 = new Product("product 2", "test description2", 2.2);
        Cart cart = new Cart();

        //When
        cart.getProducts().add(product);
        cartRepository.save(cart);
        String name = product.getName();
        cart.getProducts().add(product1);
        cartRepository.save(cart);

        //Then
        List<Product> productList = productRepository.findAll();
        assertEquals(2, productList.size());

        //CleanUp
        productRepository.deleteByName(name);
    }

    @Test
    public void testProductIsPresentWhenCartDeleted() {
        //Given
        User user = new User();
        Product product = new Product("product 1", "test description", 1.1);
        Product product1 = new Product("product 2", "test description2", 2.2);
        Cart cart = new Cart();

        cart.setUser(user);
        cart.getProducts().add(product);
        cart.getProducts().add(product1);

        //When
        cartRepository.save(cart);
        Long id = cart.getId();
        cartRepository.deleteById(id);

        //Then
        List<Product> productList = productRepository.findAll();
        assertEquals(2, productList.size());
    }

    @Test
    public void testUserWhenCartDeleted() {
        //Given
        Cart cart = new Cart();
        User user = new User();
        cart.setUser(user);

        //When
        cartRepository.save(cart);
        Long id = cart.getId();
        cartRepository.deleteById(id);

        //Then
        List<User> userList = userRepository.findAll();
        assertEquals(1, userList.size());
    }
}
