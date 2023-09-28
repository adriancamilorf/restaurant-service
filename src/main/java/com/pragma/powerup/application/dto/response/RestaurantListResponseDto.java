package com.pragma.powerup.application.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantListResponseDto {
    private String name;
    private String urlLogo;
}
