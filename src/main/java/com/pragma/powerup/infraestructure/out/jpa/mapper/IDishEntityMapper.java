package com.pragma.powerup.infraestructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.infraestructure.out.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishEntityMapper {
    DishEntity toDishEntity(DishModel dishModel);
    DishModel toDishModel(DishEntity dishEntity);
}
