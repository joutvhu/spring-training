package com.joutvhu.training.rest.controller;

import com.joutvhu.training.rest.model.view.LoginInformation;
import com.joutvhu.training.rest.model.view.RestResponse;
import com.joutvhu.training.rest.security.jwt.JwtService;
import com.joutvhu.training.rest.util.RouteConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = RouteConstants.URL_AUTH)
@Tag(name = "Auth", description = "Authentication")
public class AuthController {
    private JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Operation(description = "Get JWT token")
    @SecurityRequirements
    @PostMapping(value = RouteConstants.URL_LOGIN)
    public ResponseEntity<RestResponse<String>> create(
            @Validated @RequestBody LoginInformation information
    ) {
        return ResponseEntity.ok(new RestResponse(
                jwtService.buildJwtToken(information.getUsername(), information.getPassword()),
                HttpStatus.OK
        ));
    }
}
