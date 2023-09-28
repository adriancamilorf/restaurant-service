package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestUpdateDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.exception.InvalidRequestException;
import com.pragma.powerup.application.mapper.IDishMapper;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.CategoryModel;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.infraestructure.exception.NotDishFoundException;
import com.pragma.powerup.infraestructure.exception.OwnerInvalid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

class DishHandlerTest {

    @InjectMocks
    private DishHandler dishHandler;
    @Mock
    private IDishMapper dishMapper;
    @Mock
    private IDishServicePort dishServicePort;

    @Mock
    private IRestaurantServicePort restaurantServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @MethodSource("dishRequestDtoProvider")
    void testSaveDishWithInvalidData(DishRequestDto dishRequestDto, Class<? extends Exception> expectedException) {
        DishModel dishModel = new DishModel();
        Long userId = 1L;

        when(dishMapper.toDishModel(dishRequestDto)).thenReturn(dishModel);

        if (expectedException != null) {
            Assertions.assertThrows(expectedException, () -> {
                dishHandler.saveDish(dishRequestDto, userId);
            });
        } else {
            dishHandler.saveDish(dishRequestDto, userId);
            verify(dishServicePort).saveDish(dishModel);
        }
    }

    private static Stream<Arguments> dishRequestDtoProvider() {
        return Stream.of(
                Arguments.of(
                        DishRequestDto.builder()
                                .name("comida")
                                .category(1L)
                                .description("description")
                                .price(2000L)
                                .restaurant(1L)
                                .urlImage("url.image.co")
                                .build(),
                        null
                ),
                Arguments.of(
                        DishRequestDto.builder()
                                .name("")
                                .category(1L)
                                .description("description")
                                .price(2000L)
                                .restaurant(1L)
                                .urlImage("url.image.co")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        DishRequestDto.builder()
                                .name("comida")
                                .category(null)
                                .description("description")
                                .price(2000L)
                                .restaurant(1L)
                                .urlImage("url.image.co")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        DishRequestDto.builder()
                                .name("comida")
                                .category(1L)
                                .description("")
                                .price(2000L)
                                .restaurant(1L)
                                .urlImage("url.image.co")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        DishRequestDto.builder()
                                .name("comida")
                                .category(1L)
                                .description("description")
                                .price(null)
                                .restaurant(1L)
                                .urlImage("url.image.co")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        DishRequestDto.builder()
                                .name("comida")
                                .category(1L)
                                .description("description")
                                .price(2000L)
                                .restaurant(null)
                                .urlImage("url.image.co")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        DishRequestDto.builder()
                                .name("comida")
                                .category(1L)
                                .description("description")
                                .price(2000L)
                                .restaurant(1L)
                                .urlImage("")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        null,
                        InvalidRequestException.class
                )
        );
    }

    @ParameterizedTest
    @MethodSource("dishRequestUpdateDtoProvider")
    void testUpdateDishWithInvalidData(DishRequestUpdateDto dishRequestUpdateDto, Class<? extends Exception> expectedException) {
        Long dishId = 1L;
        DishModel dishModel = new DishModel();
        Long userId = 1L;
        when(dishMapper.toDishModel(dishRequestUpdateDto)).thenReturn(dishModel);
        when(restaurantServicePort.isOwnerOfRestaurant(1L,userId)).thenReturn(true);
        if (expectedException != null) {
            Assertions.assertThrows(expectedException, () -> {
                dishHandler.updateDish( dishId ,dishRequestUpdateDto, userId );
            });
        } else {
            dishHandler.updateDish( dishId ,dishRequestUpdateDto, userId);
            verify(dishServicePort).updateDish( dishId ,dishModel);
        }
    }

    private static Stream<Arguments> dishRequestUpdateDtoProvider() {
        return Stream.of(
                Arguments.of(
                        DishRequestUpdateDto.builder()
                                .description("descripcion a probar")
                                .price(200L)
                                .build(),
                        null
                ),
                Arguments.of(
                        DishRequestUpdateDto.builder()
                                .description("")
                                .price(200L)
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        DishRequestUpdateDto.builder()
                                .description("descripcion a probar")
                                .price(null)
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        null,
                        InvalidRequestException.class
                )
        );
    }

    @Test
    void testUpdateStatus() {

        Long dishId = 1L;
        Long userId = 2L;
        Long restaurantId = 3L;

        when(dishServicePort.getRestaurantForDish(dishId)).thenReturn(restaurantId);

        when(restaurantServicePort.isOwnerOfRestaurant(restaurantId, userId)).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> dishHandler.updateStatus(dishId, userId));

        verify(dishServicePort, times(1)).getRestaurantForDish(dishId);
        verify(restaurantServicePort, times(1)).isOwnerOfRestaurant(restaurantId, userId);
        verify(dishServicePort, times(1)).updateStatus(dishId);
    }

    @Test
    void testUpdateStatus_DishNotFound() {

        Long dishId = 1L;
        Long userId = 2L;

        when(dishServicePort.getRestaurantForDish(dishId)).thenReturn(null);

        Assertions.assertThrows(NotDishFoundException.class, () -> dishHandler.updateStatus(dishId, userId));

        verify(dishServicePort, times(1)).getRestaurantForDish(dishId);
    }

    @Test
    void testUpdateStatus_OwnerInvalid() {
        // Arrange
        Long dishId = 1L;
        Long userId = 2L;
        Long restaurantId = 3L;

        when(dishServicePort.getRestaurantForDish(dishId)).thenReturn(restaurantId);

        when(restaurantServicePort.isOwnerOfRestaurant(restaurantId, userId)).thenReturn(false);

        Assertions.assertThrows(OwnerInvalid.class, () -> dishHandler.updateStatus(dishId, userId));

        verify(dishServicePort, times(1)).getRestaurantForDish(dishId);
        verify(restaurantServicePort, times(1)).isOwnerOfRestaurant(restaurantId, userId);
    }

    @Test
    void testGetAllRestaurantsOrderedByName() {
        Long categoryId = 1L;
        String restaurantName = "Restaurante A";
        int page = 0;
        int pageSize = 10;

        DishModel dishA = new DishModel(1L, "Plato A", CategoryModel.builder().id(1L).build(), "Descripción A", 10L, RestaurantModel.builder().id(5L).name(restaurantName).build(), "http://urlA.com", true);
        DishModel dishB = new DishModel(2L, "Plato B", CategoryModel.builder().id(1L).build(), "Descripción B", 15L, RestaurantModel.builder().id(5L).name(restaurantName).build(), "http://urlB.com", true);
        DishModel dishC = new DishModel(3L, "Plato C", CategoryModel.builder().id(1L).build(), "Descripción C", 12L, RestaurantModel.builder().id(5L).name(restaurantName).build(), "http://urlC.com", true);

        Page<DishModel> dishPage = new PageImpl<>(List.of(dishA, dishB, dishC));

        when(dishMapper.toDishResponseDto(dishA)).thenReturn(
                new DishResponseDto());
        when(dishMapper.toDishResponseDto(dishB)).thenReturn(
                new DishResponseDto());
        when(dishMapper.toDishResponseDto(dishC)).thenReturn(
                new DishResponseDto());

        when(dishServicePort.findByCategoryAndRestaurantName(categoryId,restaurantName,page, pageSize)).thenReturn(dishPage);

        Page<DishResponseDto> result = dishHandler.findByCategoryAndRestaurantName(categoryId,restaurantName,0, 10);

        Assertions.assertEquals(3, result.getTotalElements());
        Assertions.assertEquals(3, result.getContent().size());
        verify(dishMapper, times(3)).toDishResponseDto(any());
    }

}