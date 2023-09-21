package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.RestaurantDto;

public interface IRestaurantHandler {
    void saveRestaurant(RestaurantDto restaurantDto);
}
