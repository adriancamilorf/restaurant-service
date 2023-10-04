package com.pragma.powerup.infraestructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.EmployeeRestaurantModel;
import com.pragma.powerup.domain.spi.IEmployeeRestaurantPersistencePort;
import com.pragma.powerup.infraestructure.exception.RestaurantNotFoundException;
import com.pragma.powerup.infraestructure.out.jpa.mapper.IEmployeeRestaurantEntityMapper;
import com.pragma.powerup.infraestructure.out.jpa.repository.IEmployeeRestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeRestaurantJpaAdapter implements IEmployeeRestaurantPersistencePort {
    private final IEmployeeRestaurantRepository employeeRestaurantRepository;
    private final IEmployeeRestaurantEntityMapper employeeRestaurantEntityMapper;

    @Override
    public void saveEmployeeRestaurant(EmployeeRestaurantModel employeeRestaurantModel){
        employeeRestaurantRepository.save(employeeRestaurantEntityMapper.toEmployeeRestaurantEntity(employeeRestaurantModel));
    }

    @Override
    public EmployeeRestaurantModel getEmployeeRestaurantByEmployeeId(Long employeeId){
        return employeeRestaurantEntityMapper.toEmployeeRestaurantModel(employeeRestaurantRepository.findByEmployeeId(employeeId).orElseThrow(RestaurantNotFoundException::new));
    }

}
