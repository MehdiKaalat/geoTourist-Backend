package com.elmehdikaalat.geoTourist.service.CategoryService;

import com.elmehdikaalat.geoTourist.domain.entity.Category;
import com.elmehdikaalat.geoTourist.domain.entity.SiteTouristique;
import com.elmehdikaalat.geoTourist.presentation.DTO.SiteTouristiqueDto;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    List<Category> addCategories(List<Category> categories) throws Exception;

}
