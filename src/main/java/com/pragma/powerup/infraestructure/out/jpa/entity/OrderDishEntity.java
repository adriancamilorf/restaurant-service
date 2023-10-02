package com.pragma.powerup.infraestructure.out.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "orders_dishes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDishEntity {

    @EmbeddedId
    private OrderDishKey id;

    @Column(nullable = false)
    private Integer quantity;
}
