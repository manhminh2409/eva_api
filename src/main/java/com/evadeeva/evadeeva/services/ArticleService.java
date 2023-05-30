package com.evadeeva.evadeeva.services;

import com.evadeeva.evadeeva.web.dto.request.ArticleRequest;
import com.evadeeva.evadeeva.web.dto.response.ArticleResponse;

import java.util.List;

public interface ArticleService {
    ArticleResponse getArticle(long articleId);
    List<ArticleResponse> getArticles(int pageNo, int pageSize, String sortBy);
    List<ArticleResponse> getArticleByCategory(int pageNo, int pageSize, String sortBy, long categoryId);
    ArticleResponse createArticle(ArticleRequest articleRequest);
}
