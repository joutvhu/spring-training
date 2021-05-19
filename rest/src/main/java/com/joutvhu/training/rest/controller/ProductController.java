package com.joutvhu.training.rest.controller;

import com.joutvhu.training.rest.model.entity.Product;
import com.joutvhu.training.rest.model.view.ProductKey;
import com.joutvhu.training.rest.service.ProductService;
import com.joutvhu.training.rest.util.RouteConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = RouteConstants.URL_PRODUCT)
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping(value = RouteConstants.URL_PRODUCT_ID)
    public ResponseEntity<Product> getOne(
            ProductKey productKey
    ) {
        return ResponseEntity.ok(productService.getOne(productKey.getProductId()));
    }

    @PostMapping
    public ResponseEntity<Product> create(
            @RequestBody Product product
    ) {
        return ResponseEntity.ok(productService.create(product));
    }

    @PutMapping(path = RouteConstants.URL_PRODUCT_ID)
    public ResponseEntity<Product> update(
            @PathVariable Map<String, Long> productKey,
            @RequestBody Product product
    ) {
        return ResponseEntity.ok(productService.update(productKey.get("productId"), product));
    }

    @DeleteMapping(path = RouteConstants.URL_PRODUCT_ID)
    public ResponseEntity delete(
            @PathVariable Long productId
    ) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }
}
