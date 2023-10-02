package com.pragma.powerup.infraestructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.infraestructure.exception.DishAlreadyExist;
import com.pragma.powerup.infraestructure.exception.InvalidDishRestaurantException;
import com.pragma.powerup.infraestructure.exception.NotDishFoundException;
import com.pragma.powerup.infraestructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infraestructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infraestructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    @Override
    public void saveDish(DishModel dishModel) {
        if(dishRepository.findByNameAndRestaurantId(dishModel.getName(),dishModel.getRestaurant().getId()).isPresent()){
            throw new DishAlreadyExist();
        }
        dishRepository.save(dishEntityMapper.toDishEntity(dishModel));
    }

    @Override
    public void updateDish(DishModel dishModel) {
        dishRepository.save(dishEntityMapper.toDishEntity(dishModel));
    }

    @Override
    public DishModel getDishById(Long id) {
        return dishEntityMapper.toDishModel(dishRepository.findById(id).orElseThrow(NotDishFoundException::new));
    }

    @Override
    public void updateStatus(Long dishId) {
        DishEntity dishEntity = dishRepository.findById(dishId).orElseThrow(NotDishFoundException::new);
        dishEntity.setActive(!dishEntity.getActive());
        dishRepository.save(dishEntity);
    }

    @Override
    public Long getRestaurantForDish(Long dishId) {
        return dishRepository.findRestaurantIdByDishId(dishId);
    }

    @Override
    public Page<DishModel> findByCategoryAndRestaurantName( Long category, String restaurantName ,Pageable pageable) {
        return dishEntityMapper.toDishModelPage(dishRepository.findByCategoryAndRestaurantName(category,restaurantName,pageable));
    }

    @Override
    public DishModel findByIdAndRestaurant(Long id, Long restaurantId){
       return dishEntityMapper.toDishModel(dishRepository.findByIdAndRestaurant(id,restaurantId).orElseThrow(InvalidDishRestaurantException::new));
    }
}
