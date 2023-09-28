package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestUpdateDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import org.springframework.data.domain.Page;

public interface IDishHandler {

    void saveDish(DishRequestDto dishRequestDto, Long userId);

    void updateDish( Long id , DishRequestUpdateDto dishRequestUpdateDto, Long userId);
    void updateStatus(Long dishId, Long userId);

    Page<DishResponseDto> findByCategoryAndRestaurantName(Long category,String restaurantName,  int page, int pageSize);
}
