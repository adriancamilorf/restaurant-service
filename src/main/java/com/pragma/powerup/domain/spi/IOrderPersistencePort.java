package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.OrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderPersistencePort {

    OrderModel saverOrder(OrderModel orderModel);

    Page<OrderModel> getByStateAndRestaurantId(String state, Long restaurantId, Pageable pageable);
}
