package com.pragma.powerup.application.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDto {
    private String name;
    private String address;
    private Long ownerId;
    private String phone;
    private String urlLogo;
    private String nit;
}
