package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.RestaurantDto;
import com.pragma.powerup.application.dto.response.RestaurantListResponseDto;
import com.pragma.powerup.domain.model.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantMapper {

    RestaurantModel toRestaurantModel(RestaurantDto restaurantDto);


    RestaurantListResponseDto toRestaurantListResponseDto(RestaurantModel restaurantModel);

}
