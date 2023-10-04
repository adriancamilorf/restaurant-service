package com.pragma.powerup.application.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderListResponseDto {
    private Long id;
    private LocalDateTime date;
    private String state;
}
