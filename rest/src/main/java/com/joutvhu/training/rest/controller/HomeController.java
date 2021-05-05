package com.joutvhu.training.rest.controller;

import com.joutvhu.training.rest.util.CommonConstants;
import com.joutvhu.training.rest.util.RouteConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = RouteConstants.URL_BASE)
public class HomeController {
    @GetMapping
    public String welcome() {
        return "<html>\n" +
                "<head>\n" +
                "  <title>Welcome</title>\n" +
                "</head>\n" +
                "<body align=\"center\">\n" +
                "  <h2>Welcome to " + CommonConstants.APPLICATION_NAME + "</h2>\n" +
                "</body>\n" +
                "</html>";
    }
}
