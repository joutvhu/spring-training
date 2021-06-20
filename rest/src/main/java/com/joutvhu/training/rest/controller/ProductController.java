package com.joutvhu.training.rest.controller;

import com.joutvhu.training.rest.model.entity.Product;
import com.joutvhu.training.rest.model.view.ProductKey;
import com.joutvhu.training.rest.model.view.RestResponse;
import com.joutvhu.training.rest.service.ProductService;
import com.joutvhu.training.rest.util.ExampleConstants;
import com.joutvhu.training.rest.util.RouteConstants;
import com.joutvhu.training.rest.validation.group.OnCreate;
import com.joutvhu.training.rest.validation.group.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

@Log4j2
@RestController
@RequestMapping(value = RouteConstants.URL_PRODUCT)
@Tag(name = "Product", description = "Product APIs")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(description = "Get all Products")
    @GetMapping
    public ResponseEntity<RestResponse<List<Product>>> getAll() {
        log.debug("Get all Products");
        return ResponseEntity.ok(new RestResponse(
                productService.getAll(),
                HttpStatus.OK
        ));
    }

    @Operation(description = "Get a Product by Product ID")
    @Parameter(name = "productId", required = true, in = ParameterIn.PATH)
    @GetMapping(value = RouteConstants.URL_PRODUCT_ID)
    public ResponseEntity<RestResponse<Product>> getOne(
            @Validated
            @Parameter(hidden = true) ProductKey productKey
    ) {
        log.debug("Get a Product by Product ID");
        return ResponseEntity.ok(new RestResponse(
                productService.getOne(productKey.getProductId()),
                HttpStatus.OK
        ));
    }

    @Operation(description = "Create a Product")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    examples = @ExampleObject(value = ExampleConstants.PRODUCT_BODY)
            ),
            required = true
    )
    @PostMapping
    public ResponseEntity<RestResponse<Product>> create(
            @Validated({OnCreate.class})
            @RequestBody Product product
    ) {
        log.debug("Create a Product");
        return ResponseEntity.ok(new RestResponse(
                productService.create(product),
                HttpStatus.OK
        ));
    }

    @Operation(description = "Update a Product")
    @Parameter(name = "productId", required = true, in = ParameterIn.PATH)
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    examples = @ExampleObject(value = ExampleConstants.PRODUCT_BODY)
            ),
            required = true
    )
    @PutMapping(path = RouteConstants.URL_PRODUCT_ID)
    public ResponseEntity<RestResponse<Product>> update(
            @Parameter(hidden = true) @PathVariable Map<String, String> productKey,
            @Validated({OnUpdate.class}) @RequestBody Product product
    ) {
        log.debug("Update a Product");
        return ResponseEntity.ok(new RestResponse(
                productService.update(Long.parseLong(productKey.get("productId")), product),
                HttpStatus.OK
        ));
    }

    @Operation(description = "Delete a Product")
    @Parameter(name = "productId", required = true, in = ParameterIn.PATH)
    @DeleteMapping(path = RouteConstants.URL_PRODUCT_ID)
    public ResponseEntity<RestResponse> delete(
            @PathVariable Long productId
    ) {
        log.debug("Delete a Product");
        productService.delete(productId);
        return ResponseEntity.ok(new RestResponse(HttpStatus.OK));
    }
}
