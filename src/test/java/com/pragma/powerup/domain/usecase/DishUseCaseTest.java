package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.usecase.DishUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class DishUseCaseTest {

    @InjectMocks
    private DishUseCase dishUseCase;

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveDish() {
        DishModel dishModel = new DishModel();
        dishUseCase.saveDish(dishModel);
        verify(dishPersistencePort, times(1)).saveDish(dishModel);
    }

    @Test
    void testUpdateDish() {
        Long dishId = 1L;
        DishModel updatedDishModel = new DishModel();
        updatedDishModel.setPrice(15L);
        updatedDishModel.setDescription("Nueva descripción del plato");
        DishModel existingDishModel = new DishModel();
        existingDishModel.setId(dishId);
        existingDishModel.setPrice(10L);
        existingDishModel.setDescription("Descripción actual del plato");

        when(dishPersistencePort.getDishById(dishId)).thenReturn(existingDishModel);

        dishUseCase.updateDish(dishId, updatedDishModel);

        verify(dishPersistencePort, times(1)).updateDish(existingDishModel);

        Assertions.assertEquals(updatedDishModel.getPrice(), existingDishModel.getPrice());
        Assertions.assertEquals(updatedDishModel.getDescription(), existingDishModel.getDescription());
    }

    @Test
    void testGetRestaurantForDish() {

        Long dishId = 1L;
        Long restaurantId = 100L;
        when(dishPersistencePort.getRestaurantForDish(dishId)).thenReturn(restaurantId);

        Long result = dishUseCase.getRestaurantForDish(dishId);

        Assertions.assertEquals(restaurantId, result);
    }


    @Test
    void testUpdateStatus() {

        Long dishId = 1L;
        doNothing().when(dishPersistencePort).updateStatus(dishId);

        dishUseCase.updateStatus(dishId);

        verify(dishPersistencePort, times(1)).updateStatus(dishId);
    }
}

