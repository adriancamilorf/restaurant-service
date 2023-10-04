package com.pragma.powerup.infraestructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.infraestructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {IRestaurantEntityMapper.class}
)
public interface IOrderEntityMapper {

    @Mapping(target = "restaurant", source = "restaurantModel")
    OrderEntity toOrderEntity(OrderModel orderModel);

    OrderModel toOrderModel(OrderEntity orderEntity);

    default Page<OrderModel> toOrderModelPage(Page<OrderEntity> entityPage) {
        return entityPage.map(this::toOrderModel);
    }

}
