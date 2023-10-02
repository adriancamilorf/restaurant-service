package com.pragma.powerup.domain.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderModel {
    private Long id;
    private Long clientId;
    private LocalDate date;
    private String state;
    private Long chefId;
    private RestaurantModel restaurantModel;
}
