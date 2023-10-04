package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IEmployeeRestaurantServicePort;
import com.pragma.powerup.domain.model.EmployeeRestaurantModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IEmployeeRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeRestaurantUseCase implements IEmployeeRestaurantServicePort {
    private final IEmployeeRestaurantPersistencePort employeeRestaurantPersistencePort;
    @Override
    public void saveEmployeeRestaurant(Long userID, Long restaurantId) {
        EmployeeRestaurantModel employeeRestaurantModel = EmployeeRestaurantModel
                .builder()
                .employeeId(userID)
                .restaurant(
                        RestaurantModel.builder().id(restaurantId).build()
                )
                .build();
        employeeRestaurantPersistencePort.saveEmployeeRestaurant(employeeRestaurantModel);
    }

    @Override
    public EmployeeRestaurantModel getEmployeeRestaurantByEmployeeId(Long employeeId){
        return employeeRestaurantPersistencePort.getEmployeeRestaurantByEmployeeId(employeeId);
    }

}
