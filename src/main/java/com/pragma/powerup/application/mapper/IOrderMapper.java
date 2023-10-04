package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.OrderListResponseDto;
import com.pragma.powerup.domain.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IOrderMapper {
    OrderListResponseDto toOrderListResponseDto(OrderModel orderModel);
}
