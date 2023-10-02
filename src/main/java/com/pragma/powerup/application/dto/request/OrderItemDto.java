package com.pragma.powerup.application.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {
    private Long dishId;
    private Integer quantity;
}
