package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.OrderRequestDto;

public interface IOrderDishHandler {
    void createOrder(Long userId, String restaurantName, OrderRequestDto orderRequestDto);
}
