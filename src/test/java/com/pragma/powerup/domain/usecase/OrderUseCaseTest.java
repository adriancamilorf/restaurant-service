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
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OrderUseCaseTest {

    @InjectMocks
    private OrderUseCase orderUseCase;
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

        OrderModel actualOrder = orderUseCase.saverOrder(clientId, restaurantId);

        assertEquals(expectedOrder, actualOrder);
    }

    private OrderModel createSampleOrder(Long clientId, Long restaurantId) {
        RestaurantModel restaurantModel = RestaurantModel.builder().id(restaurantId).build();
        return OrderModel.builder()
                .state("PENDIENTE")
                .clientId(clientId)
                .date(LocalDateTime.now())
                .restaurantModel(restaurantModel)
                .build();
    }

    @Test
    void testGetByStateAndRestaurantId() {
        String state = "PENDIENTE";
        Long restaurantId = 1L;
        int page = 0;
        int pageSize = 10;
        List<OrderModel> orders = new ArrayList<>();
        orders.add(new OrderModel());
        orders.add(new OrderModel());

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("date"));

        Page<OrderModel> expectedPage = new PageImpl<>(orders, pageable, orders.size());

        when(orderPersistencePort.getByStateAndRestaurantId(eq(state), eq(restaurantId), any(Pageable.class)))
                .thenReturn(expectedPage);
        Page<OrderModel> resultPage = orderUseCase.getByStateAndRestaurantId(state, restaurantId, page, pageSize);
        verify(orderPersistencePort, times(1)).getByStateAndRestaurantId(eq(state), eq(restaurantId), any(Pageable.class));
        assertEquals(expectedPage, resultPage);
    }

}
