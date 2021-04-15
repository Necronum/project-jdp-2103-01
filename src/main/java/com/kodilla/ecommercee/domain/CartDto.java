package com.kodilla.ecommercee.domain;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long id;
    private User user;
    private List<Product> products;
}
