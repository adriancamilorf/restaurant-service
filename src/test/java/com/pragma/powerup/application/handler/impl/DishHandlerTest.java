package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.RestaurantDto;
import com.pragma.powerup.application.exception.InvalidRequestException;
import com.pragma.powerup.application.mapper.IDishMapper;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.DishModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.stream.Stream;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DishHandlerTest {

    @InjectMocks
    private DishHandler dishHandler;
    @Mock
    private IDishMapper dishMapper;
    @Mock
    private IDishServicePort dishServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @MethodSource("dishRequestDtoProvider")
    void testSaveRestaurantWithInvalidData(DishRequestDto dishRequestDto, Class<? extends Exception> expectedException) {
        DishModel dishModel = new DishModel();

        when(dishMapper.toDishModel(dishRequestDto)).thenReturn(dishModel);

        if (expectedException != null) {
            Assertions.assertThrows(expectedException, () -> {
                dishHandler.saveDish(dishRequestDto);
            });
        } else {
            dishHandler.saveDish(dishRequestDto);
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

}