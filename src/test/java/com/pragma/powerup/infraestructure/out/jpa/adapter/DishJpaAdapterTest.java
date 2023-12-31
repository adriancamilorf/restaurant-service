package com.pragma.powerup.infraestructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.infraestructure.exception.DishAlreadyExist;
import com.pragma.powerup.infraestructure.exception.NotDishFoundException;
import com.pragma.powerup.infraestructure.out.jpa.adapter.DishJpaAdapter;
import com.pragma.powerup.infraestructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infraestructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infraestructure.out.jpa.repository.IDishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishJpaAdapterTest {

    @InjectMocks
    private DishJpaAdapter dishJpaAdapter;

    @Mock
    private IDishRepository dishRepository;

    @Mock
    private IDishEntityMapper dishEntityMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveDish_DishAlreadyExist() {
        DishModel dishModel = new DishModel();
        dishModel.setName("DishName");
        dishModel.setRestaurant(RestaurantModel.builder()
                .id(1L)
                .build()
        );

        when(dishRepository.findByNameAndRestaurantId(dishModel.getName(), dishModel.getRestaurant().getId()))
                .thenReturn(Optional.of(new DishEntity()));

        assertThrows(DishAlreadyExist.class, () -> dishJpaAdapter.saveDish(dishModel));

        verify(dishRepository, times(1)).findByNameAndRestaurantId(dishModel.getName(), dishModel.getRestaurant().getId());
        verify(dishRepository, never()).save(any(DishEntity.class));
    }

    @Test
    void testSaveDish_DishDoesNotExist() {
        DishModel dishModel = new DishModel();
        dishModel.setName("DishName");
        dishModel.setRestaurant(
                RestaurantModel.builder()
                        .id(1L)
                        .build()
        );

        when(dishRepository.findByNameAndRestaurantId(dishModel.getName(), dishModel.getRestaurant().getId()))
                .thenReturn(Optional.empty());

        dishJpaAdapter.saveDish(dishModel);

        verify(dishRepository, times(1)).findByNameAndRestaurantId(dishModel.getName(), dishModel.getRestaurant().getId());
        verify(dishRepository, times(1)).save(dishEntityMapper.toDishEntity(dishModel));
    }


    @Test
    void testUpdateDish() {
        DishModel dishModel = new DishModel();
        dishJpaAdapter.updateDish(dishModel);
        verify(dishRepository, times(1)).save(any(DishEntity.class));
    }

    @Test
    void testGetDishById() {
        Long dishId = 1L;
        DishEntity dishEntity = new DishEntity();

        when(dishRepository.findById(dishId)).thenReturn(Optional.of(dishEntity));
        DishModel result = dishJpaAdapter.getDishById(dishId);
        assertNotNull(result);

        verify(dishRepository, times(1)).findById(dishId);
    }

    @Test
    void testGetDishById_DishNotFound() {
        Long dishId = 1L;
        when(dishRepository.findById(dishId)).thenReturn(java.util.Optional.empty());
        assertThrows(NotDishFoundException.class, () -> dishJpaAdapter.getDishById(dishId));
        verify(dishRepository, times(1)).findById(dishId);
    }

    @Test
    void testUpdateStatus() {
        Long dishId = 1L;
        DishEntity dishEntity = new DishEntity();
        when(dishRepository.findById(dishId)).thenReturn(java.util.Optional.of(dishEntity));
        dishJpaAdapter.updateStatus(dishId);
        assertTrue(dishEntity.getActive());
        verify(dishRepository, times(1)).findById(dishId);
        verify(dishRepository, times(1)).save(dishEntity);
    }

    @Test
    void testUpdateStatus_DishNotFound() {
        Long dishId = 1L;
        when(dishRepository.findById(dishId)).thenReturn(java.util.Optional.empty());
        assertThrows(NotDishFoundException.class, () -> dishJpaAdapter.updateStatus(dishId));
        verify(dishRepository, times(1)).findById(dishId);
    }

    @Test
    void testGetRestaurantForDish() {
        Long dishId = 1L;
        Long restaurantId = 2L;
        when(dishRepository.findRestaurantIdByDishId(dishId)).thenReturn(restaurantId);
        Long result = dishJpaAdapter.getRestaurantForDish(dishId);
        assertEquals(restaurantId, result);
        verify(dishRepository, times(1)).findRestaurantIdByDishId(dishId);
    }

}
