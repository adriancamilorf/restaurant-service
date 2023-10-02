package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.OrderModel;

public interface IOrderPersistencePort {

    OrderModel saverOrder(OrderModel orderModel);

}
