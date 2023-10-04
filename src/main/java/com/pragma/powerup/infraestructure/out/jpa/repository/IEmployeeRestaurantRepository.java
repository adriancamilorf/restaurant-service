package com.pragma.powerup.infraestructure.out.jpa.repository;

import com.pragma.powerup.infraestructure.out.jpa.entity.EmployeeRestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEmployeeRestaurantRepository extends JpaRepository<EmployeeRestaurantEntity,Long> {

    Optional<EmployeeRestaurantEntity> findByEmployeeId(Long employeeId);

}
