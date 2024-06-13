package com.elmehdikaalat.geoTourist.presentation.controller;

import com.elmehdikaalat.geoTourist.domain.entity.Category;
import com.elmehdikaalat.geoTourist.service.CategoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<List<Category>> addCategories(@RequestBody List<Category> categories) {
        try {
            List<Category> savedCategories = categoryService.addCategories(categories);
            return ResponseEntity.ok(savedCategories);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
