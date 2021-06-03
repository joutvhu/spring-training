package com.joutvhu.training.batch.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;

import javax.sql.DataSource;

@Configuration
public class MainDbConfig {
    @Bean(name = "mainDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.main")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mainJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("mainDataSource") DataSource datasource) {
        return new JdbcTemplate(datasource);
    }

    @Bean(name = "mainDataSourceInitializer")
    @ConditionalOnProperty(name = "spring.datasource.main.driverClassName", havingValue = "org.h2.Driver")
    public DataSourceInitializer mainDataSourceInitializer(
            @Qualifier("mainDataSource") DataSource dataSource
    ) {
        return BatchDbConfig.loadScripts(dataSource, "sql/schema.sql");
    }
}
