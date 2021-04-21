package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String city;
    private String street;
    private String zipcode;
    private boolean isActiveStatus;
    private int userKey;
}
