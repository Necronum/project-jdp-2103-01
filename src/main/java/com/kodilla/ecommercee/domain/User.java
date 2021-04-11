package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @NotNull
    @Column(unique = true)
    private Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String city;
    private String street;
    private String zipcode;
}
