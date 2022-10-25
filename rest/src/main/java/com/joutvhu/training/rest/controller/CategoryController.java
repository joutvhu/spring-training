package com.joutvhu.training.rest.controller;

import com.joutvhu.training.rest.service.CategoryService;
import com.joutvhu.training.rest.util.RouteConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = RouteConstants.URL_CATEGORY)
@Tag(name = "Category", description = "Category APIs")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
}
