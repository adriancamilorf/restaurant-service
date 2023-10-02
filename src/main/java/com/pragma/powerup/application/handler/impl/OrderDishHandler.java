package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.OrderItemDto;
import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.handler.IOrderDishHandler;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.api.IOrderDishServicePort;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.OrderModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import static com.pragma.powerup.application.validation.OrderItemValidation.validateOrderItem;
import static com.pragma.powerup.application.validation.OrderItemValidation.validateQuantity;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log
public class OrderDishHandler implements IOrderDishHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IOrderServicePort orderServicePort;
    private final IOrderDishServicePort orderDishServicePort;
    private final IDishServicePort dishServicePort;

    @Override
    public void createOrder(Long userId, String restaurantName, OrderRequestDto orderRequestDto){
        List<OrderItemDto> orderItemDto = orderRequestDto.getOrderItems();
        validateOrderItem(orderItemDto);
        validateQuantity(orderItemDto);
        Long restaurantId = restaurantServicePort.getRestaurantIdByName(restaurantName);
        for (OrderItemDto orderItem: orderItemDto
             ) {
            dishServicePort.findByIdAndRestaurant(orderItem.getDishId(),restaurantId);
        }
        OrderModel order = orderServicePort.saverOrder(userId,restaurantId);
        orderDishServicePort.createOrder(order.getId(),orderRequestDto);
    }

}
