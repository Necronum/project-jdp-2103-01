package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getFirstname(),
                userDto.getLastname(),
                userDto.getEmail(),
                userDto.getPhone(),
                userDto.getCity(),
                userDto.getStreet(),
                userDto.getZipcode(),
                userDto.isActiveStatus(),
                userDto.getUserKey()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPhone(),
                user.getCity(),
                user.getStreet(),
                user.getZipcode(),
                user.isActiveStatus(),
                user.getUserKey()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream().map(this::mapToUserDto).collect(Collectors.toList());
    }
}
