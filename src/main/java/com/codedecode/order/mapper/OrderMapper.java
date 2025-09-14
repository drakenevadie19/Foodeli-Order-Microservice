package com.codedecode.order.mapper;

import com.codedecode.order.dto.OrderDTO;
import com.codedecode.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", source = "id")
    Order mapOderDTOToOrder(OrderDTO orderDTO);

    @Mapping(target = "id", source = "id")
    OrderDTO mapOrderToOrderDTO(Order order);

}
