package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.response.OrderListResponseDto;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.application.mapper.IOrderMapper;
import com.pragma.powerup.domain.api.IEmployeeRestaurantServicePort;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.model.OrderModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
@Log
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final IOrderMapper orderMapper;
    private final IEmployeeRestaurantServicePort employeeRestaurantServicePort;

    @Override
    public Page<OrderListResponseDto> getByStateAndRestaurantId(String state, Long userId, int page, int pageSize) {
        Long restaurantId = employeeRestaurantServicePort.getEmployeeRestaurantByEmployeeId(userId).getRestaurant().getId();
        Page<OrderModel> orderModelPage = orderServicePort.getByStateAndRestaurantId(state,restaurantId,page,pageSize);
        return orderModelPage.map(orderMapper::toOrderListResponseDto);
    }
}
