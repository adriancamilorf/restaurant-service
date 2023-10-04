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
@IdClass(OrderDishKey.class)
public class OrderDishEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity order;

    @Id
    @ManyToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "id")
    private DishEntity dish;

    @Column(nullable = false)
    private Integer quantity;
}
