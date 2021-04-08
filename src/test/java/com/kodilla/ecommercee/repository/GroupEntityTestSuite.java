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
        List<Product> productsList = new ArrayList<>();
        Product product = new Product();
        productsList.add(product);
        group.setProducts(productsList);

        //When
        groupRepository.save(group);

        //Then
        Long id = group.getId();
        Assert.assertNotEquals(0, id, 0000.1);

        //CleanUp
        groupRepository.deleteById(id);
    }
}
