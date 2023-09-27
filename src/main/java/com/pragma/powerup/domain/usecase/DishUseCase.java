package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;

    @Override
    public void saveDish(DishModel dishModel) {
        dishModel.setActive(true);
        dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public void updateDish(Long id,DishModel dishModel) {
        DishModel dish = dishPersistencePort.getDishById(id);
        dish.setPrice(dishModel.getPrice());
        dish.setDescription(dishModel.getDescription());
        dishPersistencePort.updateDish(dish);
    }

    @Override
    public void updateStatus(Long dishId) {
        dishPersistencePort.updateStatus(dishId);
    }

    @Override
    public Long getRestaurantForDish(Long dishId) {
        return dishPersistencePort.getRestaurantForDish(dishId);
    }
}
