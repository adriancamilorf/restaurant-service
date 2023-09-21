package com.pragma.powerup.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishModel {
    private Long id;
    private String name;
    private CategoryModel category;
    private String description;
    private Long price;
    private RestaurantModel restaurant;
    private String urlImage;
    private Boolean active;
}
