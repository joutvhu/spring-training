package com.joutvhu.training.rest.repository;

import com.joutvhu.training.rest.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
