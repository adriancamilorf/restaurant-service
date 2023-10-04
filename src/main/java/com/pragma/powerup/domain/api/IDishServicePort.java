package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.DishModel;
import org.springframework.data.domain.Page;

public interface IDishServicePort {

    void saveDish(DishModel dishModel);
    void updateDish(Long id,DishModel dishModel);

    void updateStatus(Long dishId);

    Long getRestaurantForDish(Long dishId);

    Page<DishModel> findByCategoryAndRestaurantName(Long category, String restaurantName, int page, int pageSize);

    DishModel findByIdAndRestaurant(Long id, Long restaurantId);

}
