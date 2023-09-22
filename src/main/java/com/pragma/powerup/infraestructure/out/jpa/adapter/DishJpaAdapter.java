package com.pragma.powerup.infraestructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.infraestructure.exception.DishAlreadyExist;
import com.pragma.powerup.infraestructure.exception.NotDishFoundException;
import com.pragma.powerup.infraestructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infraestructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
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
}
