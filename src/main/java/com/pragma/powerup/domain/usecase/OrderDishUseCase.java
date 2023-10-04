package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.application.dto.request.OrderItemDto;
import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.domain.api.IOrderDishServicePort;
import com.pragma.powerup.domain.model.OrderDishModel;
import com.pragma.powerup.domain.spi.IOrderDishPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class OrderDishUseCase implements IOrderDishServicePort {

    private final IOrderDishPersistencePort orderDishPersistencePort;

    @Override
    public void createOrder(Long orderId, OrderRequestDto orderRequestDto) {
        List<OrderItemDto> orderItem = orderRequestDto.getOrderItems();
        for (OrderItemDto dish : orderItem) {
            orderDishPersistencePort.saveOrder(orderId, dish.getDishId(), dish.getQuantity());
        }
    }
}
