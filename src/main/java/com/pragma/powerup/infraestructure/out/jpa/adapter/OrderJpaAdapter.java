package com.pragma.powerup.infraestructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.infraestructure.exception.PendingOrderException;
import com.pragma.powerup.infraestructure.out.jpa.entity.OrderEntity;
import com.pragma.powerup.infraestructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infraestructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.powerup.infraestructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Log
public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderMapper;

    @Override
    public OrderModel saverOrder(OrderModel orderModel) {
        List<String> states = Arrays.asList("PENDIENTE", "EN_PREPARACION", "LISTO");
        if(orderRepository.findTop1ByStateIn(states).isEmpty()) {
            OrderEntity orderEntity = orderRepository.save(orderMapper.toOrderEntity(orderModel));
            return orderMapper.toOrderModel(orderEntity);
        }
        throw new PendingOrderException();
    }

    @Override
    public Page<OrderModel> getByStateAndRestaurantId(String state, Long restaurantId, Pageable pageable) {
        RestaurantEntity restaurant = RestaurantEntity.builder().id(restaurantId).build();

        return orderMapper.toOrderModelPage(orderRepository.findByRestaurantAndState(restaurant,state,pageable));
    }
}
