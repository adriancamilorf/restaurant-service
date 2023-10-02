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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

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

        boolean result = restaurantUseCase.isOwnerOfRestaurant(restaurantId, userId);

        assertTrue(result);
    }

    @Test
    void testGetAllRestaurantsOrderedByName() {
        RestaurantModel restaurantA = new RestaurantModel(1L, "Restaurante A", "Dirección A", 1L, "1234567890", "http://urlA.com", "NIT A");
        RestaurantModel restaurantB = new RestaurantModel(2L, "Restaurante B", "Dirección B", 2L, "9876543210", "http://urlB.com", "NIT B");
        RestaurantModel restaurantC = new RestaurantModel(3L, "Restaurante C", "Dirección C", 3L, "5555555555", "http://urlC.com", "NIT C");

        Page<RestaurantModel> restaurantPage = new PageImpl<>(List.of(restaurantA, restaurantB, restaurantC));

        Mockito.when(restaurantPersistencePort.getAllRestaurantsOrderedByName(
                PageRequest.of(0, 10, Sort.by("name"))))
                .thenReturn(restaurantPage);

        Page<RestaurantModel> result = restaurantUseCase.getAllRestaurantsOrderedByName(0, 10);

        assertNotNull(result);
        assertEquals(3, result.getTotalElements());
        assertEquals(0, result.getNumber());
        assertEquals(1, result.getTotalPages());

        List<RestaurantModel> restaurantList = result.getContent();
        assertEquals(restaurantA.getName(), restaurantList.get(0).getName());
        assertEquals(restaurantB.getName(), restaurantList.get(1).getName());
        assertEquals(restaurantC.getName(), restaurantList.get(2).getName());
    }

    @Test
    void testGetRestaurantIdByName() {
        String restaurantName = "Restaurante XYZ";
        Long expectedRestaurantId = 123L;
        Mockito.when(restaurantPersistencePort.getRestaurantIdByName(restaurantName)).thenReturn(expectedRestaurantId);
        Long actualRestaurantId = restaurantUseCase.getRestaurantIdByName(restaurantName);
        assertEquals(expectedRestaurantId, actualRestaurantId);
    }

}