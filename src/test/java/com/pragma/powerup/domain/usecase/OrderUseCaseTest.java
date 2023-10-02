package com.pragma.powerup.domain.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class OrderUseCaseTest {

    @InjectMocks
    private OrderUseCase OrderUseCase;
    @Mock
    private IOrderPersistencePort orderPersistencePort;

    @BeforeEach
    public void setUp() {

    }

    @Test
    void testSaverOrder() {
        Long clientId = 1L;
        Long restaurantId = 2L;
        OrderModel expectedOrder = createSampleOrder(clientId, restaurantId);

        when(orderPersistencePort.saverOrder(any(OrderModel.class))).thenReturn(expectedOrder);

        OrderModel actualOrder = OrderUseCase.saverOrder(clientId, restaurantId);

        assertEquals(expectedOrder, actualOrder);
    }

    private OrderModel createSampleOrder(Long clientId, Long restaurantId) {
        RestaurantModel restaurantModel = RestaurantModel.builder().id(restaurantId).build();
        return OrderModel.builder()
                .state("PENDIENTE")
                .clientId(clientId)
                .date(LocalDate.now())
                .restaurantModel(restaurantModel)
                .build();
    }
}
