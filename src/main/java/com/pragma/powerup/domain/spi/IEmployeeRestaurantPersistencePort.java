package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.EmployeeRestaurantModel;

public interface IEmployeeRestaurantPersistencePort {

    void saveEmployeeRestaurant(EmployeeRestaurantModel employeeRestaurantModel);

    EmployeeRestaurantModel getEmployeeRestaurantByEmployeeId(Long employeeId);
}
