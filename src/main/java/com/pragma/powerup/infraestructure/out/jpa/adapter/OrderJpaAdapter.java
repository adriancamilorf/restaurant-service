package com.pragma.powerup.infraestructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.infraestructure.exception.PendingOrderException;
import com.pragma.powerup.infraestructure.out.jpa.entity.OrderEntity;
import com.pragma.powerup.infraestructure.out.jpa.mapper.IOrderMapper;
import com.pragma.powerup.infraestructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Log
public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderMapper orderMapper;

    @Override
    public OrderModel saverOrder(OrderModel orderModel) {
        List<String> states = Arrays.asList("PENDIENTE", "EN_PREPARACION", "LISTO");
        if(orderRepository.findByStateIn(states).isEmpty()) {
            OrderEntity orderEntity = orderRepository.save(orderMapper.toOrderEntity(orderModel));
            return orderMapper.toOrderModel(orderEntity);
        }
        throw new PendingOrderException();
    }
}
