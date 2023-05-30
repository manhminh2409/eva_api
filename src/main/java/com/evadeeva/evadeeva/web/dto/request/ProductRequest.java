package com.evadeeva.evadeeva.web.dto.request;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class ProductRequest {
    private String name;

    private String description;

    private String material;

    private int price;

    private int salePrice;

    private Long categoryId;

    private Long userId;

    private List<ColorRequest> colors;

    private List<ProductImageRequest> images;
}
