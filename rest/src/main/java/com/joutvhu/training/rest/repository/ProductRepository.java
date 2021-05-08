package com.joutvhu.training.rest.repository;

import com.joutvhu.training.rest.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
