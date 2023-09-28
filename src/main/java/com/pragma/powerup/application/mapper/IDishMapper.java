package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestUpdateDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.domain.model.CategoryModel;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishMapper {
    DishModel toDishModel(DishRequestDto dishRequestDto);

    DishModel toDishModel(DishRequestUpdateDto dishRequestUpdateDto);

    DishResponseDto toDishResponseDto(DishModel dishModel);

    default RestaurantModel mapIdToRestaurantModel(Long restaurantId) {
        if (restaurantId == null) {
            return null;
        }
        return RestaurantModel.builder()
                .id(restaurantId)
                .build();
    }

    default CategoryModel mapIdToCategoryModel(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        return CategoryModel.builder()
                .id(categoryId)
                .build();
    }

}
