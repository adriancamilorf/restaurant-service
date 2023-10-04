package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

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
                .date(LocalDateTime.now())
                .restaurantModel(restaurantModel)
                .build();
        return orderPersistencePort.saverOrder(orderModel);
    }

    @Override
    public Page<OrderModel> getByStateAndRestaurantId(String state, Long restaurantId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("date"));
        return orderPersistencePort.getByStateAndRestaurantId(state,restaurantId,pageable);
    }
}
