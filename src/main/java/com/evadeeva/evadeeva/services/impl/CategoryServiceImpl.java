package com.evadeeva.evadeeva.services.impl;

import com.evadeeva.evadeeva.mapper.CategoryMapper;
import com.evadeeva.evadeeva.models.Category;
import com.evadeeva.evadeeva.repositories.CategoryRepository;
import com.evadeeva.evadeeva.services.CategoryService;
import com.evadeeva.evadeeva.web.dto.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final int STATUS_ACTIVE = 1;
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }



    @Override
    public List<CategoryResponse> getCategories() {
        List<Category> categories = categoryRepository.findByStatus(STATUS_ACTIVE);
        return categories.stream().map(categoryMapper::mapModelToResponse).toList();
    }
}
