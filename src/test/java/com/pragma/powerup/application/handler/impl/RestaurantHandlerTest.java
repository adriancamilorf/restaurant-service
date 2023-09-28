package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RestaurantDto;
import com.pragma.powerup.application.dto.response.RestaurantListResponseDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

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

    @Test
    void testGetAllRestaurantsOrderedByName() {
        RestaurantModel restaurantA = new RestaurantModel(1L, "Restaurante A", "Dirección A", 1L, "1234567890", "http://urlA.com", "NIT A");
        RestaurantModel restaurantB = new RestaurantModel(2L, "Restaurante B", "Dirección B", 2L, "9876543210", "http://urlB.com", "NIT B");
        RestaurantModel restaurantC = new RestaurantModel(3L, "Restaurante C", "Dirección C", 3L, "5555555555", "http://urlC.com", "NIT C");

        Page<RestaurantModel> restaurantPage = new PageImpl<>(List.of(restaurantA, restaurantB, restaurantC));

        when(restaurantMapper.toRestaurantListResponseDto(restaurantA)).thenReturn(
                new RestaurantListResponseDto("Restaurante A", "http://urlA.com"));
        when(restaurantMapper.toRestaurantListResponseDto(restaurantB)).thenReturn(
                new RestaurantListResponseDto("Restaurante B", "http://urlB.com"));
        when(restaurantMapper.toRestaurantListResponseDto(restaurantC)).thenReturn(
                new RestaurantListResponseDto("Restaurante C", "http://urlC.com"));

        when(restaurantServicePort.getAllRestaurantsOrderedByName(0, 10)).thenReturn(restaurantPage);

        Page<RestaurantListResponseDto> result = restaurantHandler.getAllRestaurantsOrderedByName(0, 10);

        Assertions.assertEquals(3, result.getTotalElements());
        Assertions.assertEquals(3, result.getContent().size());
        verify(restaurantMapper, times(3)).toRestaurantListResponseDto(any());
    }


}