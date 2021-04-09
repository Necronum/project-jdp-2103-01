package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GroupEntityTestSuite {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testDeleteById(){
        //Given
        Group group = new Group("Test group");

        //When
        groupRepository.save(group);
        Long id = group.getId();
        groupRepository.deleteById(id);

        //Then
        List<Group> groupList = groupRepository.findAll();
        Assert.assertEquals(0,groupList.size());
    }

    @Test
    public void testFindById(){
        //Given
        Group group = new Group("Test group");

        //When
        groupRepository.save(group);

        //Then
        Long id = group.getId();
        Optional<Group> groupOptional = groupRepository.findById(id);
        Assert.assertEquals(id,groupOptional.get().getId());

        //CleanUp
        groupRepository.deleteById(id);
    }

    @Test
    public void testFindByName(){
        //Given
        Group group = new Group("Test group");

        //When
        groupRepository.save(group);

        //Then
        Long id = group.getId();
        String name = group.getName();
        List<Group> foundGroup = groupRepository.findByName(name);
        Assert.assertEquals(name ,foundGroup.get(0).getName());

        //CleanUp
        groupRepository.deleteById(id);
    }

    @Test
    public void testGroupNameUpdate(){
        //Given
        Group group = new Group("Test group");

        //When
        groupRepository.save(group);
        String updatedName = "Altered test group";
        group.setName(updatedName);
        groupRepository.save(group);

        //Then
        Long id = group.getId();
        String name = group.getName();
        List<Group> foundGroup = groupRepository.findByName(name);
        Assert.assertEquals(1 ,foundGroup.size());
        Assert.assertEquals(updatedName ,foundGroup.get(0).getName());

        //CleanUp
        groupRepository.deleteById(id);
    }

    @Test
    public void testGroupWithProduct(){
        //Given
        Group group = new Group("Test group");
        Product product = new Product("Shirt", "Shirt with dog", 200.20);
        group.getProducts().add(product);
        product.setGroup(group);

        //When
        groupRepository.save(group);

        //Then
        Long id = group.getId();
        Long productId = product.getId();
        Optional<Group> groupFromRepository = groupRepository.findById(id);
        Optional<Product> productFromRepository = productRepository.findById(productId);
        Assert.assertTrue(groupFromRepository.isPresent());
        Assert.assertTrue(productFromRepository.isPresent());

        //CleanUp
        groupRepository.deleteById(id);
    }

    @Test
    public void testProductAfterGroupDelete(){
        //Given
        Group group = new Group("Test group");
        Product product = new Product("Shirt", "Shirt with dog", 200.20);
        group.getProducts().add(product);
        product.setGroup(group);

        //When
        groupRepository.save(group);
        Long id = group.getId();
        groupRepository.deleteById(id);

        //Then
        Long productId = product.getId();
        Optional<Group> groupFromRepository = groupRepository.findById(id);
        Optional<Product> productFromRepository = productRepository.findById(productId);
        Assert.assertFalse(groupFromRepository.isPresent());
        Assert.assertFalse(productFromRepository.isPresent());

        //CleanUp
        //groupRepository.deleteById(id);
    }
}
