package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="GROUPS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    private List<Product> products = new ArrayList<>();

    public Group(String name){
        this.name = name;
    }
}
