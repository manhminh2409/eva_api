package com.evadeeva.evadeeva.web;

import com.evadeeva.evadeeva.services.CategoryService;
import com.evadeeva.evadeeva.web.dto.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryRest {
    private final CategoryService categoryService;

    @Autowired
    public CategoryRest(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public List<CategoryResponse> getCategories(){
        return categoryService.getCategories();
    }
}
