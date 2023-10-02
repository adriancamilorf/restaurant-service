package com.pragma.powerup.infraestructure.out.jpa.repository;

import com.pragma.powerup.infraestructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity,Long> {

    Optional<RestaurantEntity> findByName(String name);
    Optional<RestaurantEntity> findByNit(String nit);

    Page<RestaurantEntity> findAllByOrderByName(Pageable pageable);

}
