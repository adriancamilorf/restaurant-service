package com.pragma.powerup.infraestructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.infraestructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infraestructure.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishEntityMapper {
    DishEntity toDishEntity(DishModel dishModel);
    DishModel toDishModel(DishEntity dishEntity);

    default Page<DishModel> toDishModelPage(Page<DishEntity> entityPage) {
        return entityPage.map(this::toDishModel);
    }
}
