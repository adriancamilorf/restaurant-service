package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.EmployeeRestaurantModel;
import com.pragma.powerup.domain.spi.IEmployeeRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRestaurantUseCaseTest {
    @Mock
    private IEmployeeRestaurantPersistencePort employeeRestaurantPersistencePort;

    private EmployeeRestaurantUseCase employeeRestaurantUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeRestaurantUseCase = new EmployeeRestaurantUseCase(employeeRestaurantPersistencePort);
    }

    @Test
    void testSaveEmployeeRestaurant() {
        Long userId = 1L;
        Long restaurantId = 2L;
        employeeRestaurantUseCase.saveEmployeeRestaurant(userId, restaurantId);
        Mockito.verify(employeeRestaurantPersistencePort, Mockito.times(1)).saveEmployeeRestaurant(ArgumentMatchers.any());
    }

    @Test
    void testGetEmployeeRestaurantByEmployeeId() {
        Long employeeId = 1L;
        EmployeeRestaurantModel expectedEmployeeRestaurant = new EmployeeRestaurantModel();
        Mockito.when(employeeRestaurantPersistencePort.getEmployeeRestaurantByEmployeeId(employeeId))
                .thenReturn(expectedEmployeeRestaurant);
        EmployeeRestaurantModel result = employeeRestaurantUseCase.getEmployeeRestaurantByEmployeeId(employeeId);
        Mockito.verify(employeeRestaurantPersistencePort, Mockito.times(1)).getEmployeeRestaurantByEmployeeId(employeeId);
        assertEquals(expectedEmployeeRestaurant, result);
    }
}