package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.RestaurantModel;
import org.springframework.data.domain.Page;

public interface IRestaurantServicePort {
    void saveRestaurant(RestaurantModel restaurantModel);
    Boolean isOwnerOfRestaurant(Long restaurantId, Long userId);

    Page<RestaurantModel> getAllRestaurantsOrderedByName(int page, int pageSize);

}
