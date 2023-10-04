package com.pragma.powerup.infraestructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.EmployeeRestaurantModel;
import com.pragma.powerup.infraestructure.out.jpa.entity.EmployeeRestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeRestaurantEntityMapper {
    EmployeeRestaurantEntity toEmployeeRestaurantEntity(EmployeeRestaurantModel employeeRestaurantModel);
    EmployeeRestaurantModel toEmployeeRestaurantModel(EmployeeRestaurantEntity employeeRestaurantEntity);
}