package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name="GROUPS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Column(name = "ID", unique = true)
    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "ID",
            fetch = FetchType.EAGER
    )
    private List<Product> products = new ArrayList<>();

    public Group(String name){
        this.name = name;
    }
}
