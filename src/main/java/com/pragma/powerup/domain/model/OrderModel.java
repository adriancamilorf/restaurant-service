package com.pragma.powerup.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderModel {
    private Long id;
    private Long clientId;
    private LocalDateTime date;
    private String state;
    private EmployeeRestaurantModel chefId;
    private RestaurantModel restaurantModel;
    private List<OrderDishModel> orderDishModelList;
}
