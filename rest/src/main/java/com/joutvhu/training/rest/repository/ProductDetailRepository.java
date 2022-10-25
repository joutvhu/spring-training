package com.joutvhu.training.rest.repository;

import com.joutvhu.training.rest.model.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
}
