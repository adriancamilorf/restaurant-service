package com.pragma.powerup.infraestructure.out.jpa.adapter;

import com.pragma.powerup.domain.spi.IOrderDishPersistencePort;
import com.pragma.powerup.infraestructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infraestructure.out.jpa.entity.OrderDishEntity;
import com.pragma.powerup.infraestructure.out.jpa.entity.OrderDishKey;
import com.pragma.powerup.infraestructure.out.jpa.entity.OrderEntity;
import com.pragma.powerup.infraestructure.out.jpa.repository.IOrderDishRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderDishJpaAdapter implements IOrderDishPersistencePort {

    private final IOrderDishRepository orderDishRepository;

    @Override
    public void saveOrder(Long orderId, Long dishId, Integer quantity) {
        OrderEntity orderEntity = OrderEntity.builder().id(orderId).build();
        DishEntity dishEntity = DishEntity.builder().id(dishId).build();
        OrderDishKey orderDishKey= new OrderDishKey();
        orderDishKey.setOrderEntity(orderEntity);
        orderDishKey.setDishEntity(dishEntity);
        OrderDishEntity orderDishEntity = OrderDishEntity
                .builder()
                .quantity(quantity)
                .id(orderDishKey)
                .build();
        orderDishRepository.save(orderDishEntity);
    }
}
