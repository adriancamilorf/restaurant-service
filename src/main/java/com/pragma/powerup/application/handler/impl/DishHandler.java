package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestUpdateDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.application.mapper.IDishMapper;
import static com.pragma.powerup.application.validation.DishValidation.dishRequestDtoValidation;
import static com.pragma.powerup.application.validation.DishValidation.DishRequestUpdateDtoValidation;
import com.pragma.powerup.domain.api.IDishServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final IDishMapper dishMapper;

    @Override
    public void saveDish(DishRequestDto dishRequestDto) {
        dishRequestDtoValidation(dishRequestDto);
        dishServicePort.saveDish(dishMapper.toDishModel(dishRequestDto));
    }

    @Override
    public void updateDish( Long id, DishRequestUpdateDto dishRequestUpdateDto) {
        DishRequestUpdateDtoValidation(dishRequestUpdateDto);
        dishServicePort.updateDish(id, dishMapper.toDishModel(dishRequestUpdateDto));
    }
}
