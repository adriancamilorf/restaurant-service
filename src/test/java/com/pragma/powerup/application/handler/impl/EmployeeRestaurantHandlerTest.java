package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.EmployeeRestaurantDto;
import com.pragma.powerup.domain.api.IEmployeeRestaurantServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.usecase.EmployeeRestaurantUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;


class EmployeeRestaurantHandlerTest {

    @Mock
    private IRestaurantServicePort restaurantServicePort;

    @Mock
    private IEmployeeRestaurantServicePort employeeRestaurantServicePort;

    @InjectMocks
    private EmployeeRestaurantUseCase employeeRestaurantUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }
    @Test
    void testSaveEmployeeRestaurant() {
        EmployeeRestaurantDto employeeRestaurantDto = new EmployeeRestaurantDto();
        Long ownerID = 1L;

        Mockito.when(restaurantServicePort.isOwnerOfRestaurant(eq(employeeRestaurantDto.getRestaurantId()), (ownerID)))
                .thenReturn(true);

        employeeRestaurantUseCase.saveEmployeeRestaurant(1L, ownerID);

        verify(restaurantServicePort, times(1)).isOwnerOfRestaurant((employeeRestaurantDto.getRestaurantId()), eq(ownerID));

        verify(employeeRestaurantServicePort, times(1))
                .saveEmployeeRestaurant((employeeRestaurantDto.getEmployeeID()), eq(employeeRestaurantDto.getRestaurantId()));

    }
}