package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.OrderModel;
import org.springframework.data.domain.Page;

public interface IOrderServicePort {

    OrderModel saverOrder(Long clientId, Long restaurantId);

    Page<OrderModel> getByStateAndRestaurantId(String state, Long restaurantId, int page,int pageSize );

}
