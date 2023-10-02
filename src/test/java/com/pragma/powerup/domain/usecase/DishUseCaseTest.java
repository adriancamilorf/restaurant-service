package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.CategoryModel;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @Test
    void testFindByCategoryAndRestaurantName() {

        Long categoryId = 1L;
        String restaurantName = "Restaurante A";
        int page = 0;
        int pageSize = 10;

        DishModel dishA = new DishModel(1L, "Plato A", CategoryModel.builder().id(1L).build(), "Descripción A", 10L, RestaurantModel.builder().id(5L).name(restaurantName).build(), "http://urlA.com", true);
        DishModel dishB = new DishModel(2L, "Plato B", CategoryModel.builder().id(1L).build(), "Descripción B", 15L, RestaurantModel.builder().id(5L).name(restaurantName).build(), "http://urlB.com", true);
        DishModel dishC = new DishModel(3L, "Plato C", CategoryModel.builder().id(1L).build(), "Descripción C", 12L, RestaurantModel.builder().id(5L).name(restaurantName).build(), "http://urlC.com", true);

        Page<DishModel> dishPage = new PageImpl<>(List.of(dishA, dishB, dishC));

        Mockito.when(dishPersistencePort.findByCategoryAndRestaurantName(
                        categoryId,
                        restaurantName,
                        PageRequest.of(page, pageSize, Sort.by("name"))))
                .thenReturn(dishPage);

        Page<DishModel> result = dishUseCase.findByCategoryAndRestaurantName(categoryId, restaurantName, 0, 10);

        assertNotNull(result);
        assertEquals(3, result.getTotalElements());
        assertEquals(0, result.getNumber());
        assertEquals(1, result.getTotalPages());
        List<DishModel> dishList = result.getContent();
        assertEquals(dishA.getName(), dishList.get(0).getName());
        assertEquals(dishB.getName(), dishList.get(1).getName());
        assertEquals(dishC.getName(), dishList.get(2).getName());

    }

    @Test
    void testFindByIdAndRestaurant() {
        Long dishId = 1L;
        Long restaurantId = 2L;
        DishModel expectedDish = createSampleDish();
        when(dishPersistencePort.findByIdAndRestaurant(dishId, restaurantId)).thenReturn(expectedDish);
        DishModel resultDish = dishUseCase.findByIdAndRestaurant(dishId, restaurantId);
        assertNotNull(resultDish);
        assertEquals(expectedDish, resultDish);
    }

    private DishModel createSampleDish() {
        return DishModel
                .builder()
                .id(1L)
                .name("Sample Dish")
                .build();
    }

}

