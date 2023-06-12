package com.evadeeva.evadeeva.web.dto.request;

import lombok.Data;

@Data
public class OrderItemRequest {
    private long sizeId;
    private int quantity;
    private int sellPrice;
}
