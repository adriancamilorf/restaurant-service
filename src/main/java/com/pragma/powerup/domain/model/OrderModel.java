package com.pragma.powerup.domain.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderModel {
    private Long id;
    private Long clientId;
    private LocalDate date;
    private Integer state;
    private Long chefId;
    private RestaurantModel restaurantModel;
}
