package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.RestaurantDto;
import com.pragma.powerup.application.dto.response.RestaurantListResponseDto;
import org.springframework.data.domain.Page;

public interface IRestaurantHandler {
    void saveRestaurant(RestaurantDto restaurantDto);
    Page<RestaurantListResponseDto> getAllRestaurantsOrderedByName(int page, int pageSize);

}
