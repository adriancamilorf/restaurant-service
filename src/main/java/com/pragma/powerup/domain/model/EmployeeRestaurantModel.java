package com.pragma.powerup.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRestaurantModel {
    private Long id;
    private Long employeeId;
    private RestaurantModel restaurant;
}
