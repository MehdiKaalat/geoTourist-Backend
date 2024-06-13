package com.elmehdikaalat.geoTourist.service.CategoryService;

import com.elmehdikaalat.geoTourist.domain.entity.Category;
import com.elmehdikaalat.geoTourist.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Category> addCategories(List<Category> categories) throws Exception {
        try {
            return repository.saveAll(categories);
        } catch (Exception e) {
            throw new Exception("Failed to save categories", e);
        }
    }
}
