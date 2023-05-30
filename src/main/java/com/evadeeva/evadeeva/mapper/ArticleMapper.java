package com.evadeeva.evadeeva.mapper;

import com.evadeeva.evadeeva.models.Article;
import com.evadeeva.evadeeva.models.ArticleImage;
import com.evadeeva.evadeeva.web.dto.request.ArticleRequest;
import com.evadeeva.evadeeva.web.dto.response.ArticleImageResponse;
import com.evadeeva.evadeeva.web.dto.response.ArticleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ArticleMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "images", source = "articleImages")
    ArticleResponse mapToResponse(Article article);

    ArticleImageResponse mapImageToResponse(ArticleImage articleImage);

    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "user.id", source = "userId")
    Article mapRequestToModel(ArticleRequest articleRequest);
}
