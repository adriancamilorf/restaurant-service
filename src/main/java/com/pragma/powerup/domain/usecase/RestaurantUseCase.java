package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;

    @Override
    public void saveRestaurant(RestaurantModel restaurantModel) {
        restaurantPersistencePort.saveRestaurant(restaurantModel);
    }

    @Override
    public Boolean isOwnerOfRestaurant(Long restaurantId, Long userId) {
        return restaurantPersistencePort.isOwnerOfRestaurant(restaurantId,userId);
    }

    @Override
    public Page<RestaurantModel> getAllRestaurantsOrderedByName(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("name"));
        return restaurantPersistencePort.getAllRestaurantsOrderedByName(pageable);
    }

    @Override
    public Long getRestaurantIdByName(String name) {
        return restaurantPersistencePort.getRestaurantIdByName(name);
    }
}
