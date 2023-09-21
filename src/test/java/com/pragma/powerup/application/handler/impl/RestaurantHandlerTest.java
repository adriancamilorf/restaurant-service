package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RestaurantDto;
import com.pragma.powerup.application.exception.InvalidRequestException;
import com.pragma.powerup.application.mapper.IRestaurantMapper;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.RestaurantModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RestaurantHandlerTest {
    private IRestaurantServicePort restaurantServicePort;
    private IRestaurantMapper restaurantMapper;
    private RestaurantHandler restaurantHandler;

    @BeforeEach
    public void setUp() {
        restaurantServicePort = Mockito.mock(IRestaurantServicePort.class);
        restaurantMapper = Mockito.mock(IRestaurantMapper.class);
        restaurantHandler = new RestaurantHandler(restaurantServicePort, restaurantMapper);
    }

    @ParameterizedTest
    @MethodSource("restaurantDtoProvider")
    void testSaveRestaurantWithInvalidData(RestaurantDto restaurantDto, Class<? extends Exception> expectedException) {
        RestaurantModel restaurantModelSimulated = new RestaurantModel();

        when(restaurantMapper.toRestaurantModel(restaurantDto)).thenReturn(restaurantModelSimulated);

        if (expectedException != null) {
            Assertions.assertThrows(expectedException, () -> {
                restaurantHandler.saveRestaurant(restaurantDto);
            });
        } else {
            restaurantHandler.saveRestaurant(restaurantDto);
            verify(restaurantServicePort).saveRestaurant(restaurantModelSimulated);
        }
    }

    private static Stream<Arguments> restaurantDtoProvider() {
        return Stream.of(
                Arguments.of(
                        RestaurantDto.builder()
                                .address("av 2 #1-2")
                                .phone("3137623509")
                                .nit("1234")
                                .name("las delicias")
                                .ownerId(2L)
                                .urlLogo("logo.com")
                                .build(),
                        null
                ),
                Arguments.of(
                        RestaurantDto.builder()
                                .address("av 2 #1-2")
                                .phone("31623509")
                                .nit("1234")
                                .name("las delicias")
                                .ownerId(2L)
                                .urlLogo("logo.com")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        RestaurantDto.builder()
                                .address("av 2 #1-2")
                                .phone("3137623509")
                                .nit("1234")
                                .name("1234")
                                .ownerId(2L)
                                .urlLogo("logo.com")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        RestaurantDto.builder()
                                .address("")
                                .phone("3137623509")
                                .nit("1234")
                                .name("delicias")
                                .ownerId(2L)
                                .urlLogo("logo.com")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        RestaurantDto.builder()
                                .address("av 2 #1-2")
                                .phone("3137623509")
                                .nit("1234")
                                .name("delicias")
                                .ownerId(2L)
                                .urlLogo("")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        RestaurantDto.builder()
                                .address("av 2 #1-2")
                                .phone("3137623509")
                                .nit("1234a")
                                .name("delicias")
                                .ownerId(2L)
                                .urlLogo("logo.com")
                                .build(),
                        InvalidRequestException.class
                ),
                Arguments.of(
                        RestaurantDto.builder()
                                .address("av 2 #1-2")
                                .phone("3137623509")
                                .nit("1234")
                                .name("delicias")
                                .ownerId(null)
                                .urlLogo("logo.com")
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