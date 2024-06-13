package com.elmehdikaalat.geoTourist.domain.repository;

import com.elmehdikaalat.geoTourist.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
