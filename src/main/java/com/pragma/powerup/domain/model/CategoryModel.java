package com.pragma.powerup.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryModel {
    private Long id;
    private String name;
    private String description;
}
