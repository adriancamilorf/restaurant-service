package com.pragma.powerup.infraestructure.out.jpa.repository;

import com.pragma.powerup.infraestructure.out.jpa.entity.OrderEntity;
import com.pragma.powerup.infraestructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findTop1ByStateIn(List<String> states);


    Page<OrderEntity> findByRestaurantAndState(RestaurantEntity restaurant, String state, Pageable pageable);

}
