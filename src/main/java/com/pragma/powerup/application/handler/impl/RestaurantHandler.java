package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RestaurantDto;
import com.pragma.powerup.application.dto.response.RestaurantListResponseDto;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import com.pragma.powerup.application.mapper.IRestaurantMapper;
import com.pragma.powerup.application.validation.RestaurantValidation;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.RestaurantModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class RestaurantHandler implements IRestaurantHandler {
    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantMapper restaurantMapper;
    @Override
    public void saveRestaurant(RestaurantDto restaurantDto) {
        RestaurantValidation.restaurantDtoValidate(restaurantDto);
        restaurantServicePort.saveRestaurant(restaurantMapper.toRestaurantModel(restaurantDto));
    }

    @Override
    public Page<RestaurantListResponseDto> getAllRestaurantsOrderedByName(int page, int pageSize) {
        Page<RestaurantModel> restaurantModelPage = restaurantServicePort.getAllRestaurantsOrderedByName(page,pageSize);
        return restaurantModelPage.map(restaurantMapper::toRestaurantListResponseDto);
    }

}
