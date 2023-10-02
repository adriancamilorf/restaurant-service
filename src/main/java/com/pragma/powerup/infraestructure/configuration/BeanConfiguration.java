package com.pragma.powerup.infraestructure.configuration;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.api.IOrderDishServicePort;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IOrderDishPersistencePort;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.domain.usecase.DishUseCase;
import com.pragma.powerup.domain.usecase.OrderDishUseCase;
import com.pragma.powerup.domain.usecase.OrderUseCase;
import com.pragma.powerup.domain.usecase.RestaurantUseCase;
import com.pragma.powerup.infraestructure.feign.user.RoleServiceRequest;
import com.pragma.powerup.infraestructure.out.jpa.adapter.DishJpaAdapter;
import com.pragma.powerup.infraestructure.out.jpa.adapter.OrderDishJpaAdapter;
import com.pragma.powerup.infraestructure.out.jpa.adapter.OrderJpaAdapter;
import com.pragma.powerup.infraestructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.powerup.infraestructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infraestructure.out.jpa.mapper.IOrderMapper;
import com.pragma.powerup.infraestructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.infraestructure.out.jpa.repository.IDishRepository;
import com.pragma.powerup.infraestructure.out.jpa.repository.IOrderDishRepository;
import com.pragma.powerup.infraestructure.out.jpa.repository.IOrderRepository;
import com.pragma.powerup.infraestructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final RoleServiceRequest roleServiceRequest;
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final IOrderDishRepository orderDishRepository;
    private final IOrderRepository orderRepository;
    private final IOrderMapper orderMapper;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository,restaurantEntityMapper,roleServiceRequest);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort());
    }
    @Bean
    public IDishPersistencePort dishPersistencePort(){
        return new DishJpaAdapter(dishRepository,dishEntityMapper);
    }

    @Bean
    public IDishServicePort dishServicePort(){
        return new DishUseCase(dishPersistencePort());
    }

    @Bean
    public IOrderDishPersistencePort orderDishPersistencePort(){
        return new OrderDishJpaAdapter(orderDishRepository);
    }

    @Bean
    public IOrderDishServicePort orderDishServicePort(){
        return new OrderDishUseCase(orderDishPersistencePort());
    }
    @Bean
    public IOrderPersistencePort orderPersistencePort(){
        return new OrderJpaAdapter(orderRepository, orderMapper  );
    }

    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(orderPersistencePort());
    }
}
