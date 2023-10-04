package com.pragma.powerup.application.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRestaurantDto {
    private Long employeeID;
    private Long restaurantId;
}
