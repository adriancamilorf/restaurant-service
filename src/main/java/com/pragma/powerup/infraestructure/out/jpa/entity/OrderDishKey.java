package com.pragma.powerup.infraestructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderDishKey implements Serializable {

    @ManyToOne(optional = false)
    @JoinColumn(name = "dish_id")
    private DishEntity dishEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;
}
