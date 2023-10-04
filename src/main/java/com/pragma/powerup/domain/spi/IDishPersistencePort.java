package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.DishModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDishPersistencePort {
    void saveDish(DishModel dishModel);
    void updateDish(DishModel dishModel);

    DishModel getDishById(Long id);

    void updateStatus(Long dishId);

    Long getRestaurantForDish(Long dishId);

    Page<DishModel> findByCategoryAndRestaurantName(Long category, String restaurantName, Pageable pageable);

    DishModel findByIdAndRestaurant(Long id, Long restaurantId);

}
