package com.pragma.powerup.domain.spi;

public interface IOrderDishPersistencePort {

    void saveOrder(Long orderId,Long dishId,Integer quantity);

}
