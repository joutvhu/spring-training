package com.joutvhu.training.rest.service;

import com.joutvhu.training.rest.model.entity.Product;
import com.joutvhu.training.rest.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getOne(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(Long id, Product product) {
        Product p = getOne(id);
        p.setProductName(product.getProductName());
        productRepository.save(p);
        return p;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
