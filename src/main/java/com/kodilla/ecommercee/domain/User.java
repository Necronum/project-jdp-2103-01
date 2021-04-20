package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true)
    private Long id;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    @Column(unique = true)
    private String email;

    private String phone;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String zipcode;

    @NotNull
    private boolean isActiveStatus;

    @NotNull
    private int userKey;

    @OneToMany(
            targetEntity = Order.class,
            cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    private List<Order> orderList;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.EAGER)
    private Cart cart;

    public User(@NotNull String firstname, @NotNull String lastname, @NotNull String email, String phone, @NotNull String city, @NotNull String street, @NotNull String zipcode, @NotNull boolean isActiveStatus, @NotNull int userKey) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.isActiveStatus = isActiveStatus;
        this.userKey = userKey;
    }

    public User() {
        this.firstname = "";
        this.lastname = "";
        this.email = "";
        this.city = "";
        this.street = "";
        this.zipcode = "";
        this.isActiveStatus = false;
        this.userKey = -1;
    }
}
