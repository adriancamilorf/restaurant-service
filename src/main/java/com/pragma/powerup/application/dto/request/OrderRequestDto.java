package com.pragma.powerup.application.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDto {
    private List<OrderItemDto> orderItems;
}