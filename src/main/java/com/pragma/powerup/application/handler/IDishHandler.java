package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestUpdateDto;

public interface IDishHandler {

    void saveDish(DishRequestDto dishRequestDto);

    void updateDish( Long id , DishRequestUpdateDto dishRequestUpdateDto);
}
