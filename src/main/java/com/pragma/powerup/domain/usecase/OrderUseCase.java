package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;

    @Override
    public OrderModel saverOrder(Long clientId, Long restaurantId) {
        RestaurantModel restaurantModel = RestaurantModel.builder().id(restaurantId).build();
        OrderModel orderModel = OrderModel
                .builder()
                .state("PENDIENTE")
                .clientId(clientId)
                .date(LocalDate.now())
                .restaurantModel(restaurantModel)
                .build();
        return orderPersistencePort.saverOrder(orderModel);
    }
}
