package com.evadeeva.evadeeva.web.dto.response;

import lombok.Data;

@Data
public class OrderItemResponse {
    private long id;
    private int quantity;
    private int sellPrice;
    private ProductResponse product;
}
