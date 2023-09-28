package com.pragma.powerup.application.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishRequestDto {
    private String name;
    private Long category;
    private String description;
    private Long price;
    private Long restaurant;
    private String urlImage;
}
