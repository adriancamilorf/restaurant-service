package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantUseCaseTest {
    @InjectMocks
    private RestaurantUseCase restaurantUseCase;
    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveRestaurant_ShouldCallSaveRestaurantOnPersistencePort() {

        RestaurantModel restaurantModel = RestaurantModel.builder()
                .address("av 2 #1-2")
                .phone("3137623509")
                .nit("1234")
                .name("las delicias")
                .ownerId(2L)
                .urlLogo("logo.com")
                .build();

        restaurantUseCase.saveRestaurant(restaurantModel);

        Mockito.verify(restaurantPersistencePort, Mockito.times(1)).saveRestaurant(restaurantModel);
    }

    @Test
    void isOwnerOfRestaurantTest(){
        Long restaurantId = 1L;
        Long userId = 100L;
        Mockito.when(restaurantPersistencePort.isOwnerOfRestaurant(restaurantId, userId)).thenReturn(true);

        boolean result = restaurantPersistencePort.isOwnerOfRestaurant(restaurantId, userId);

        assertTrue(result);
    }

}