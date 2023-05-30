package com.evadeeva.evadeeva.mapper;

import com.evadeeva.evadeeva.models.*;
import com.evadeeva.evadeeva.web.dto.request.ProductRequest;
import com.evadeeva.evadeeva.web.dto.response.*;
import org.mapstruct.*;

@Mapper
public interface ProductMapper{
    //map danh sách ProductResponse với danh sách Product
    @Mapping(target = "colors", source = "colors")
    @Mapping(target = "images", source = "productImages")
    @Mapping(target = "category",source = "productCategory.id")
    @Mapping(target = "author",source = "productAuthor.id")
    ProductResponse mapModelToResponse(Product product);

    @Mapping(target = "sizes", source = "sizes")
    ColorResponse mapColorToColorResponse(Color color);

    SizeResponse mapSizeToSizeResponse(Size size);

    ProductImageResponse mapImageToImageResponse(ProductImage productImage);

    @Mapping(target = "productCategory.id", source = "categoryId")
    @Mapping(target = "productAuthor.id", source = "userId")
    Product mapRequestedToModel(ProductRequest productRequest);

    //Update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "productCategory.id", source = "categoryId")
    @Mapping(target = "productAuthor.id", source = "userId")
    void updateModel(@MappingTarget Product product, ProductRequest productRequest);
    //
    CategoryResponse mapModelToResponse(Category category);
    @Mapping(target = "roles",source = "roles")
    UserResponse mapModelToResponse(User user);
}
