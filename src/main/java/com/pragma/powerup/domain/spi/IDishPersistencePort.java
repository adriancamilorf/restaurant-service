package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.DishModel;

public interface IDishPersistencePort {
    void saveDish(DishModel dishModel);
    void updateDish(DishModel dishModel);

    DishModel getDishById(Long id);

    void updateStatus(Long dishId);

    Long getRestaurantForDish(Long dishId);

}
