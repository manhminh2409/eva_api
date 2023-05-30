package com.evadeeva.evadeeva.services.impl;

import com.evadeeva.evadeeva.mapper.CategoryMapper;
import com.evadeeva.evadeeva.models.Category;
import com.evadeeva.evadeeva.repositories.CategoryRepository;
import com.evadeeva.evadeeva.services.CategoryService;
import com.evadeeva.evadeeva.web.dto.response.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.evadeeva.evadeeva.config.Constants.ACTIVE_STATUS;


@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryResponse> getCategories() {
        List<Category> categories = categoryRepository.findByStatus(ACTIVE_STATUS);
        if (!categories.isEmpty()){
            return categories.stream()
                    .map(categoryMapper::mapModelToResponse)
                    .toList();
        }else {
            return null;
        }
    }

    @Override
    public List<CategoryResponse> getCategoriesByType(int type) {
        List<Category> categories;
        if (type != -1) {
            categories = categoryRepository.findAllByStatusAndType(ACTIVE_STATUS, type);
        }else {
            categories = categoryRepository.findByStatus(ACTIVE_STATUS);
        }

        if (categories != null) {
            return categories.stream()
                    .map(categoryMapper::mapModelToResponse)
                    .toList();
        }
        return null;
    }
}
