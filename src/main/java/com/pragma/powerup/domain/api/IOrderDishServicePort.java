package com.pragma.powerup.domain.api;

import com.pragma.powerup.application.dto.request.OrderRequestDto;

public interface IOrderDishServicePort {

    void createOrder(Long orderId, OrderRequestDto orderRequestDto);

}
