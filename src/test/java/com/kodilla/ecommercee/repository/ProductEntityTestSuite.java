package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductEntityTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void testFindById() {
        //Given
        Product product = new Product();

        //When
        productRepository.save(product);

        //Then
        Long id = product.getId();
        Optional<Product> productId = productRepository.findById(id);
        assertEquals(id, productId.get().getId());

        //CleanUp
        productRepository.deleteById(id);
    }

    @Test
    public void testUpdateProduct() {
        //Given
        Product product = new Product("Test Product", "Test Description", 25.4);

        //When
        Product updatedProduct = productRepository.save(product);
        updatedProduct.setPrice(45.5);

        //Then
        assertEquals(45.5, updatedProduct.getPrice(), 0000000.1);
    }

    @Test
    public void testDeleteProduct() {
        //Given
        Product product = new Product("Test Product", "Test Description", 25.4);

        //When
        productRepository.save(product);
        productRepository.deleteByName(product.getName());
        List<Product> deletedProduct = productRepository.findAll();

        //Then
        assertEquals(0, deletedProduct.size());
    }

    @Test
    public void testDeleteProductKeepGroup() {
        //Given
        Product product = new Product("Test Product", "Test Description", 25.4);
        Group group = new Group("Test Group");
        group.getProducts().add(product);
        product.setGroup(group);

        //When
        productRepository.save(product);
        productRepository.deleteByName(product.getName());
        Long groupId = product.getGroup().getId();
        Optional<Group> repositoryGroup = groupRepository.findById(groupId);

        //Then
        assertTrue(repositoryGroup.isPresent());
    }

    @Test
    public void testDeleteProductKeepCart() {
        //Given
        Product product = new Product("Test Product", "Test Description", 25.4);
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        List<Cart> carts = new ArrayList<>();
        carts.add(cart1);
        carts.add(cart2);
        cart1.getProducts().add(product);
        cart2.getProducts().add(product);
        product.setCarts(carts);

        //When
        productRepository.save(product);
        productRepository.deleteByName(product.getName());

        assertEquals(2, carts.size());
    }
}
