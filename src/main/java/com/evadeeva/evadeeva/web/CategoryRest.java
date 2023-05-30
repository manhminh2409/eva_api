package com.evadeeva.evadeeva.web;

import com.evadeeva.evadeeva.services.CategoryService;
import com.evadeeva.evadeeva.web.dto.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<?> getCategories(@RequestParam(value = "type", defaultValue = "-1") int type){
        try {
            List<CategoryResponse> categoryResponses = categoryService.getCategoriesByType(type);
            if(!categoryResponses.isEmpty()){
                return ResponseEntity.ok(categoryResponses);
            }else {
                return ResponseEntity.ok("Danh mục đang được thêm mới!");
            }
        }catch (Exception e){
            return new ResponseEntity<>("Lỗi!", HttpStatus.BAD_REQUEST);
        }
    }

}
