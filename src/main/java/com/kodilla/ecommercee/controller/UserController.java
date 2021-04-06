package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/user")
public class UserController {

    @PostMapping(value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        //do nothing
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        //do nothing
    }

    @PutMapping(value = "editUser")
    public UserDto editUser(@RequestBody UserDto userDto) {
        return new UserDto("Edited name", "Edited surname", "Edited nick", 1L);
    }

    @GetMapping(value = "getUser")
    public UserDto getUser(@RequestParam Long userId) {
        return new UserDto("Test name", "Test surname", "Test nick", 1L);
    }
}
