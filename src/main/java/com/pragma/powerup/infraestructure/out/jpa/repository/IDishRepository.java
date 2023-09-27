package com.pragma.powerup.infraestructure.out.jpa.repository;

import com.pragma.powerup.infraestructure.out.jpa.entity.DishEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {
    Optional<DishEntity> findByNameAndRestaurantId(String name, Long restaurantId);

    @Query("SELECT d.restaurant.id FROM DishEntity d WHERE d.id = :dishId")
    Long findRestaurantIdByDishId(@Param("dishId") Long dishId);

}
