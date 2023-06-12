package com.evadeeva.evadeeva.mapper;

import com.evadeeva.evadeeva.models.OrderItem;
import com.evadeeva.evadeeva.web.dto.request.OrderItemRequest;
import com.evadeeva.evadeeva.web.dto.response.OrderItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderItemMapper {
    OrderItemResponse mapToResponse(OrderItem orderItem);
    @Mapping(target = "size.id", source = "sizeId")
    OrderItem mapToModel(OrderItemRequest orderItemRequest);
}
