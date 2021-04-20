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
@NoArgsConstructor
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

}
