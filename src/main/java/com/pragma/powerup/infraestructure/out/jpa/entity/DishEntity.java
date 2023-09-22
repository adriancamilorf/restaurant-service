package com.pragma.powerup.infraestructure.out.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dishes")
@Getter
@Setter
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Long price;
    @Column(nullable = false)
    private String urlImage;
    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean active;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_category")
    private CategoryEntity category;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_restaurant")
    private RestaurantEntity  restaurant;
}
