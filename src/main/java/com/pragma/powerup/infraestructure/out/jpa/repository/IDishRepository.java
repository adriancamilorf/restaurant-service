package com.pragma.powerup.infraestructure.out.jpa.repository;

import com.pragma.powerup.infraestructure.out.jpa.entity.DishEntity;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {
    Optional<DishEntity> findByNameAndRestaurantId(String name, Long restaurantId);

    @Query("SELECT d.restaurant.id FROM DishEntity d WHERE d.id = :dishId")
    Long findRestaurantIdByDishId(@Param("dishId") Long dishId);

    @Query("SELECT d FROM DishEntity d WHERE d.category.id = :categoryId AND d.restaurant.name = :restaurantName")
    Page<DishEntity> findByCategoryAndRestaurantName(@Param("categoryId") Long categoryId, @Param("restaurantName") String restaurantName, Pageable pageable);

    @Query("SELECT d FROM DishEntity d WHERE d.id = :id AND d.restaurant.id = :restaurantId")
    Optional<DishEntity> findByIdAndRestaurant(Long id,Long restaurantId);

}
