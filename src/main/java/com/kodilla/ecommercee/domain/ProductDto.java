package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String productName;
    private String description;
    private Double price;
    private Long groupId;
}
