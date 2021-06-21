package com.joutvhu.training.rest.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableAspectJAutoProxy
@EnableConfigurationProperties
@ImportResource("classpath:application-config.xml")
public class AppConfig {
    @Bean("demoBean1")
    @Profile("local")
    public String demoBean1() {
        return "1";
    }

    @Bean("demoBean2")
    @Profile("!test")
    public String demoBean2() {
        return "2";
    }

    @Bean("demoBean3")
    @Profile("local")
    public String demoBean3Local() {
        return "31";
    }

    @Bean("demoBean3")
    @Profile("prod")
    public String demoBean3Prod() {
        return "32";
    }
}
