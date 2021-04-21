package com.kodilla.ecommercee.domain;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long id;
    private Long userId;
    private List<Long> productsId;
}
