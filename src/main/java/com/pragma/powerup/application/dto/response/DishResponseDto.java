package com.pragma.powerup.application.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishResponseDto {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private String urlImage;
}
