package com.evadeeva.evadeeva.mapper;

import com.evadeeva.evadeeva.models.Orders;
import com.evadeeva.evadeeva.web.dto.response.OrdersResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = OrderItemMapper.class)
public interface OrdersMapper {
    @Mapping(target = "items", source = "orderItems")
    OrdersResponse mapToResponse(Orders orders);
}
