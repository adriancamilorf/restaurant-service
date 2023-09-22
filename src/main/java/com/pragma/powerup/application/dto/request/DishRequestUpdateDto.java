package com.pragma.powerup.application.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishRequestUpdateDto {
    private Long price;
    private String description;
}
