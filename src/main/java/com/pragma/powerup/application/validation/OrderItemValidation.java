package com.pragma.powerup.application.validation;

import com.pragma.powerup.application.dto.request.OrderItemDto;
import com.pragma.powerup.application.exception.InvalidRequestException;

import java.util.List;

public class OrderItemValidation {

    private OrderItemValidation(){
    }

    public static void validateQuantity(List<OrderItemDto> orderItemDto){
        for(OrderItemDto item: orderItemDto ){
            if(item.getQuantity()<1){
                throw new InvalidRequestException("Todos los itemas deben tener al menos un elemento");
            }
        }
    }

    public static void validateOrderItem(List<OrderItemDto> orderItemDto){
        if(orderItemDto==null || orderItemDto.isEmpty()){
            throw new InvalidRequestException("Seleccione uno o mas elementos para el pedido");
        }
    }

}
