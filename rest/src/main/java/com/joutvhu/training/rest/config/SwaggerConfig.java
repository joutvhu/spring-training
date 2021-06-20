package com.joutvhu.training.rest.config;

import com.joutvhu.training.rest.security.jwt.JwtService;
import com.joutvhu.training.rest.util.CommonConstants;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @see <a href="https://swagger.io">Swagger</a>
 * <a href="https://springdoc.org">SpringDoc</a>
 */
@Configuration
public class SwaggerConfig {
    /**
     * Defines the Swagger OpenAPI bean.
     * Now you can test it in your browser by visiting: ../{context-path}/swagger-ui.html
     *
     * @author joutvhu
     */
    @Bean
    public OpenAPI swaggerAPI() {
        OpenAPI result = new OpenAPI()
                .info(apiInfo());

        result
                .schemaRequirement(
                        "jwtToken",
                        new SecurityScheme()
                                .name(JwtService.TOKEN_HEADER)
                                .description("Json Web Token")
                                .type(SecurityScheme.Type.HTTP)
                                .in(SecurityScheme.In.HEADER)
                                .scheme(JwtService.TOKEN_PREFIX)
                                .bearerFormat("JWT")
                )
                .security(
                        Collections.singletonList(
                                new SecurityRequirement()
                                        .addList("jwtToken")
                        )
                );

        return result;
    }

    /**
     * Defines the Api information.
     *
     * @return Info
     * @author joutvhu
     */
    private Info apiInfo() {
        return new Info()
                .title(CommonConstants.APPLICATION_NAME + " API")
                .description(CommonConstants.APPLICATION_NAME + " API Documentation")
                .version("1.0.0")
                .termsOfService("Terms of service")
                .contact(
                        new Contact()
                                .name("Giao Ho")
                                .email("joutvhu@gmail.com")
                                .url("https://github.com/joutvhu")
                )
                .license(
                        new License()
                                .name("MIT License")
                                .url("https://github.com/joutvhu/spring-training/blob/main/LICENSE")
                );
    }
}
