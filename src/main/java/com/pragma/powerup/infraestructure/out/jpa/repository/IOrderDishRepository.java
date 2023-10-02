package com.pragma.powerup.infraestructure.out.jpa.repository;

import com.pragma.powerup.infraestructure.out.jpa.entity.OrderDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDishRepository extends JpaRepository<OrderDishEntity,Long> {
}
