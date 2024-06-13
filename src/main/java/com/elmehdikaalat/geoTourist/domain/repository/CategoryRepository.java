package com.elmehdikaalat.geoTourist.domain.repository;

import com.elmehdikaalat.geoTourist.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
