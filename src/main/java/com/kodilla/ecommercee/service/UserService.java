package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.exception.ResourceNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> getUsers() {
        List<User> userList = userRepository.findAll();
        return userMapper.mapToUserDtoList(userList);
    }

    public UserDto getUser(Long id) {
        return userRepository.findById(id).map(userMapper::mapToUserDto)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    public void addUser(UserDto userDto) {
        userRepository.save(userMapper.mapToUser(userDto));
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        return userRepository.findById(id).map(user -> {
            User updatedUser = userMapper.mapToUser(userDto);
            userRepository.save(updatedUser);
            return userMapper.mapToUserDto(updatedUser);
        }).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " not found")
        );
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
