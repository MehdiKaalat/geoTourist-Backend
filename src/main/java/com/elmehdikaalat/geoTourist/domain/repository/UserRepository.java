package com.elmehdikaalat.geoTourist.domain.repository;

import com.elmehdikaalat.geoTourist.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
