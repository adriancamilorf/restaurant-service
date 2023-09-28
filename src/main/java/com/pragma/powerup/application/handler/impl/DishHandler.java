package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestUpdateDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.application.mapper.IDishMapper;
import static com.pragma.powerup.application.validation.DishValidation.dishRequestDtoValidation;
import static com.pragma.powerup.application.validation.DishValidation.DishRequestUpdateDtoValidation;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.infraestructure.exception.NotDishFoundException;
import com.pragma.powerup.infraestructure.exception.OwnerInvalid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final IDishMapper dishMapper;
    private final IRestaurantServicePort restaurantServicePort;

    @Override
    public void saveDish(DishRequestDto dishRequestDto, Long userID ) {
        dishRequestDtoValidation(dishRequestDto);
        dishServicePort.saveDish(dishMapper.toDishModel(dishRequestDto));
    }

    @Override
    public void updateDish( Long restaurantId, DishRequestUpdateDto dishRequestUpdateDto, Long userId) {
        boolean isOwner = restaurantServicePort.isOwnerOfRestaurant(restaurantId,userId);
        if(!isOwner){
            throw new OwnerInvalid();
        }
        DishRequestUpdateDtoValidation(dishRequestUpdateDto);
        dishServicePort.updateDish(restaurantId, dishMapper.toDishModel(dishRequestUpdateDto));
    }

    @Override
    public void updateStatus(Long dishId, Long userId ) {
        Long restaurantId= dishServicePort.getRestaurantForDish(dishId);
        if(restaurantId==null){
            throw new NotDishFoundException();
        }
        boolean isOwner = restaurantServicePort.isOwnerOfRestaurant(restaurantId,userId);
        if(!isOwner){
            throw new OwnerInvalid();
        }
        dishServicePort.updateStatus(dishId);
    }

    @Override
    public Page<DishResponseDto> findByCategoryAndRestaurantName(Long category,String restaurantName, int page, int pageSize) {
        Page<DishModel> dishModelPage = dishServicePort.findByCategoryAndRestaurantName(category, restaurantName, page,pageSize);
        return dishModelPage.map(dishMapper::toDishResponseDto);
    }
}
