package com.pragma.powerup.domain.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDishModel {
    private DishModel dish;
    private OrderModel order;
    private Integer quantity;
}
