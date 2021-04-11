package com.kodilla.ecommercee.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "PRODUCT_ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;


    @NotNull
    @Column(name = "PRICE")
    private double price;

    @ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "GROUP_ID")
    @JsonIgnoreProperties("products")
    private Group group;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    @JsonIgnoreProperties("products")
    private List<Cart> carts = new ArrayList<>();

    public Product(@NotNull String name, String description, @NotNull double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}