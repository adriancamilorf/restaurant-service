package com.pragma.powerup.domain.api;


import com.pragma.powerup.domain.model.EmployeeRestaurantModel;

public interface IEmployeeRestaurantServicePort {
    void saveEmployeeRestaurant(Long userID, Long restaurantId);

    EmployeeRestaurantModel getEmployeeRestaurantByEmployeeId(Long employeeId);
}
