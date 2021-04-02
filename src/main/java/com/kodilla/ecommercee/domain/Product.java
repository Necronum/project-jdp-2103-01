package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product {

    private Long id;
    private String productName;

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID")
    public Long getId() {
        return this.id;
    }
    @Column(name = "PRODUCT_NAME" )
    public String getProductName() {
        return this.productName;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setProductName(String productName) {
        this.productName = productName;
    }
}
