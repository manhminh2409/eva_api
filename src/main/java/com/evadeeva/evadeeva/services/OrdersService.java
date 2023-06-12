package com.evadeeva.evadeeva.services;

import com.evadeeva.evadeeva.web.dto.request.OrderItemRequest;
import com.evadeeva.evadeeva.web.dto.response.OrdersResponse;

public interface OrdersService {
    OrdersResponse getOrderByType(long userId, int type);
    OrdersResponse addItemToCart(long userId, OrderItemRequest orderItemRequest);
}
