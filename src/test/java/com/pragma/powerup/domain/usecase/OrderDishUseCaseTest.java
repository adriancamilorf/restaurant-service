package com.pragma.powerup.domain.usecase;

import static org.mockito.Mockito.*;
import com.pragma.powerup.application.dto.request.OrderItemDto;
import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.domain.spi.IOrderDishPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class OrderDishUseCaseTest {

    @InjectMocks
    private OrderDishUseCase orderService;
    @Mock
    private IOrderDishPersistencePort orderDishPersistencePort;

    @BeforeEach
    public void setUp() {

    }

    @Test
    void testCreateOrder() {
        Long orderId = 1L;
        OrderRequestDto orderRequestDto = createSampleOrderRequestDto();
        orderService.createOrder(orderId, orderRequestDto);
        verify(orderDishPersistencePort, times(2)).saveOrder(eq(orderId), anyLong(), anyInt());
    }

    private OrderRequestDto createSampleOrderRequestDto() {
        OrderItemDto item1 = new OrderItemDto(1L, 2);
        OrderItemDto item2 = new OrderItemDto(2L, 3);
        return new OrderRequestDto(List.of(item1, item2));
    }
}
