package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.EmployeeRestaurantDto;
import com.pragma.powerup.application.handler.IEmployeeRestaurantHandler;
import com.pragma.powerup.domain.api.IEmployeeRestaurantServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
@Log
public class EmployeeRestaurantHandler implements IEmployeeRestaurantHandler {
    private final IEmployeeRestaurantServicePort employeeRestaurantServicePort;
    private final IRestaurantServicePort restaurantServicePort;

    @Override
    public void saveEmployeeRestaurant(EmployeeRestaurantDto employeeRestaurantDto, Long ownerID) {
        Long restaurantId = employeeRestaurantDto.getRestaurantId();
        Long userID = employeeRestaurantDto.getEmployeeID();
        restaurantServicePort.isOwnerOfRestaurant(restaurantId, ownerID );
        employeeRestaurantServicePort.saveEmployeeRestaurant(userID,restaurantId);
    }
}
