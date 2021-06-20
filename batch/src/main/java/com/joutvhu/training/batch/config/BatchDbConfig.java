package com.joutvhu.training.batch.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;

@Configuration
public class BatchDbConfig {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.batch")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "batchDataSource")
    @Primary
    public DataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name = "batchJdbcTemplate")
    public NamedParameterJdbcTemplate jdbcTemplate(@Qualifier("batchDataSource") DataSource datasource) {
        return new NamedParameterJdbcTemplate(datasource);
    }

    @Primary
    @Bean(name = "batchTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("batchDataSource") DataSource datasource) {
        return new DataSourceTransactionManager(datasource);
    }

    /**
     * Create Datasource Initializer
     */
    public static DataSourceInitializer loadScripts(DataSource dataSource, String... scripts) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        for (String script : scripts) populator.addScript(new ClassPathResource(script));
        populator.setSqlScriptEncoding(StandardCharsets.UTF_8.name());
        initializer.setDatabasePopulator(populator);
        initializer.setEnabled(true);
        initializer.afterPropertiesSet();
        return initializer;
    }

    @Bean(name = "batchDataSourceInitializer")
    @ConditionalOnProperty(name = "spring.datasource.batch.driverClassName", havingValue = "org.h2.Driver")
    public DataSourceInitializer mainDataSourceInitializer(
            @Qualifier("batchDataSource") DataSource dataSource
    ) {
        return loadScripts(
                dataSource,
                "org/springframework/batch/core/schema-drop-h2.sql",
                "org/springframework/batch/core/schema-h2.sql"
        );
    }
}
