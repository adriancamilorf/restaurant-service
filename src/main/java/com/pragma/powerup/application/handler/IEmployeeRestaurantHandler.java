package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.EmployeeRestaurantDto;

public interface IEmployeeRestaurantHandler {
    void saveEmployeeRestaurant(EmployeeRestaurantDto employeeRestaurantDto, Long ownerId);
}
