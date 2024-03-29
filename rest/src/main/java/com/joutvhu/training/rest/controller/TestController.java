package com.joutvhu.training.rest.controller;

import com.joutvhu.training.rest.model.view.RestResponse;
import com.joutvhu.training.rest.service.AopService;
import com.joutvhu.training.rest.service.AsyncService;
import com.joutvhu.training.rest.service.BeanLifecycle;
import com.joutvhu.training.rest.util.RouteConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = RouteConstants.URL_TEST)
@Tag(name = "Test", description = "Test")
public class TestController {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AopService aopService;

    @Autowired
    private AsyncService asyncService;

    @Operation(description = "Test filecycle")
    @GetMapping(path = RouteConstants.URL_LIFECYCLE)
    public ResponseEntity<RestResponse> filecycle() {
        log.debug("Test filecycle");
        BeanLifecycle beanLifecycle = (BeanLifecycle) applicationContext.getBean("beanLifecycle");
        beanLifecycle.ready();
        return ResponseEntity.ok(new RestResponse(HttpStatus.OK));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(description = "Test auth")
    @GetMapping(path = RouteConstants.URL_TEST_AUTH)
    public ResponseEntity<RestResponse<String>> testAuth() {
        return ResponseEntity.ok(new RestResponse("Welcome", HttpStatus.OK));
    }

    @Operation(description = "Test aop")
    @GetMapping(path = RouteConstants.URL_AOP)
    public ResponseEntity<RestResponse<String>> testAspect() {
        aopService.workWithPublic();
        aopService.notWorkWithFinal();
        aopService.notWorkWhenCallInASameObject();
        return ResponseEntity.ok(new RestResponse(HttpStatus.OK));
    }

    @Operation(description = "Test async")
    @GetMapping(path = RouteConstants.URL_ASYNC)
    public ResponseEntity<RestResponse<String>> testAsync() {
        log.debug("Start testAsync");
        asyncService.testAsync1();
        asyncService.testAsync2();
        asyncService.testAsync3();
        asyncService.testAsync4();
        asyncService.testAsync5();
        asyncService.testAsync6();
        log.debug("End testAsync");
        return ResponseEntity.ok(new RestResponse(HttpStatus.OK));
    }
}
