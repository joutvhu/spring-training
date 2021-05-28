package com.joutvhu.training.rest.controller;

import com.joutvhu.training.rest.util.CommonConstants;
import com.joutvhu.training.rest.util.RouteConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = RouteConstants.URL_BASE)
@Tag(name = "Home", description = "Home")
public class HomeController {
    @Value("${server.servlet.context-path}")
    private String serverPath;
    @Value("${springdoc.swagger-ui.path}")
    private String swaggerPath;

    /**
     * Use to check server is running
     * Return welcome response for GET request.
     *
     * @return welcome message
     */
    @GetMapping
    @Operation(summary = "Welcome to " + CommonConstants.APPLICATION_NAME, hidden = true)
    public String welcome() {
        return "<html>\n" +
                "<head>\n" +
                "  <title>Welcome</title>\n" +
                "</head>\n" +
                "<body align=\"center\">\n" +
                "  <h2>Welcome to " + CommonConstants.APPLICATION_NAME + "</h2>\n" +
                "  <p><a href=\"" + serverPath + swaggerPath + "\">Go To Swagger</a></p>\n" +
                "</body>\n" +
                "</html>";
    }
}
