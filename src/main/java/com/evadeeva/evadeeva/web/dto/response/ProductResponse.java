package com.evadeeva.evadeeva.web.dto.response;

import com.evadeeva.evadeeva.models.Category;
import com.evadeeva.evadeeva.models.Color;
import com.evadeeva.evadeeva.models.ProductImage;
import com.evadeeva.evadeeva.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ProductResponse {
    private Long id;

    private String name;

    private String description;

    private String sku;

    private String material;

    private int price;

    private int salePrice;

    private Date modifiedDate;

    private Date createdDate;

    private int status;

    private long author;

    private long category;

    private List<ColorResponse> colors;

    private List<ProductImageResponse> images;
}
