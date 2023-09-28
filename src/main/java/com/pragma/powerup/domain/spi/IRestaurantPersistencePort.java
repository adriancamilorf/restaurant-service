package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.RestaurantModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRestaurantPersistencePort {
    void saveRestaurant(RestaurantModel restaurantModel);
    Boolean isOwnerOfRestaurant(Long restaurantId, Long userId);
    Page<RestaurantModel> getAllRestaurantsOrderedByName(Pageable pageable);

}
