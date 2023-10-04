package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.response.OrderListResponseDto;
import org.springframework.data.domain.Page;

public interface IOrderHandler {
    public Page<OrderListResponseDto> getByStateAndRestaurantId(String state, Long userId, int page, int pageSize);
}
