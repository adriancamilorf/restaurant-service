package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Override
    public Page<DishModel> findByCategoryAndRestaurantName(Long category, String restaurantName, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("name"));
        return dishPersistencePort.findByCategoryAndRestaurantName(category,restaurantName,pageable);
    }
}
