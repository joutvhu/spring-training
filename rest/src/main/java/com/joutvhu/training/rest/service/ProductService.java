package com.joutvhu.training.rest.service;

import com.joutvhu.training.rest.aop.Benchmark;
import com.joutvhu.training.rest.model.entity.Product;
import com.joutvhu.training.rest.model.entity.ProductDetail;
import com.joutvhu.training.rest.repository.ProductDetailRepository;
import com.joutvhu.training.rest.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getOne(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Benchmark
    public Product update(Long id, Product product) {
        Product p = getOne(id);
        p.setProductName(product.getProductName());
        productRepository.save(p);
        return p;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Product edit(long id) {
        Product product = productRepository.getById(id);
        if (product != null) {
            product.setProductName("Product X");
            productRepository.save(product);

            for (ProductDetail detail : product.getDetails()) {
                detail.setDescription("Description Y");
                productDetailRepository.save(detail);
            }
        }
        return productRepository.getById(id);
    }
}
