package com.pragma.powerup.infraestructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.OrderDishModel;
import com.pragma.powerup.infraestructure.out.jpa.entity.OrderDishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IOrderDishEntityMapper {

    OrderDishModel toOrderDishModel(OrderDishEntity orderDishEntity);

}
