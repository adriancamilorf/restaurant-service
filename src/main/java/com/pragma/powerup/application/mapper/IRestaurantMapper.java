package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.RestaurantDto;
import com.pragma.powerup.application.dto.response.RestaurantListResponseDto;
import com.pragma.powerup.domain.model.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantMapper {

    RestaurantDto toRestaurantDto(RestaurantModel restaurantModel);
    RestaurantModel toRestaurantModel(RestaurantDto restaurantDto);
    default Page<RestaurantListResponseDto> toRestaurantListResponseDtoPage(Page<RestaurantModel> source) {
        List<RestaurantListResponseDto> restaurantListResponseDto = source.map(this::toRestaurantListResponseDto).getContent();
        return new PageImpl<>(restaurantListResponseDto, source.getPageable(), source.getTotalElements());
    }

    RestaurantListResponseDto toRestaurantListResponseDto(RestaurantModel restaurantModel);

}
