package com.evadeeva.evadeeva.services;

import com.evadeeva.evadeeva.web.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getCategories();
    List<CategoryResponse> getCategoriesByType(int type);
}
