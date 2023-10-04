package com.pragma.powerup.domain.api;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.domain.model.OrderDishModel;
import org.springframework.data.domain.Page;

public interface IOrderDishServicePort {

    void createOrder(Long orderId, OrderRequestDto orderRequestDto);

}
