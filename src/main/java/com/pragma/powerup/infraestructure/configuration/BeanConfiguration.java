package com.pragma.powerup.infraestructure.configuration;

import com.pragma.powerup.domain.api.*;
import com.pragma.powerup.domain.spi.*;
import com.pragma.powerup.domain.usecase.*;
import com.pragma.powerup.infraestructure.feign.user.UserServiceRequest;
import com.pragma.powerup.infraestructure.out.jpa.adapter.*;
import com.pragma.powerup.infraestructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infraestructure.out.jpa.mapper.IEmployeeRestaurantEntityMapper;
import com.pragma.powerup.infraestructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.powerup.infraestructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.infraestructure.out.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final UserServiceRequest userServiceRequest;
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final IOrderDishRepository orderDishRepository;
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderMapper;
    private final IEmployeeRestaurantRepository employeeRestaurantRepository;
    private final IEmployeeRestaurantEntityMapper employeeRestaurantEntityMapper;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository,restaurantEntityMapper, userServiceRequest);
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

    @Bean
    public IEmployeeRestaurantPersistencePort employeeRestaurantPersistencePort(){
        return new EmployeeRestaurantJpaAdapter(employeeRestaurantRepository,employeeRestaurantEntityMapper);
    }

    @Bean
    public IEmployeeRestaurantServicePort employeeRestaurantServicePort(){
        return new EmployeeRestaurantUseCase(employeeRestaurantPersistencePort());
    }
}
