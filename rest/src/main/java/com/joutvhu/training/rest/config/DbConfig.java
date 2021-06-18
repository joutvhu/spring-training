package com.joutvhu.training.rest.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.joutvhu.training.rest.repository"},
        entityManagerFactoryRef = "mainEntityManagerFactory",
        transactionManagerRef = "mainTransactionManager"
)
public class DbConfig {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.main")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * For old version
     *
     * @Bean(name = "mainDataSource")
     * @Primary
     * @ConfigurationProperties(prefix = "spring.datasource.main")
     * public DataSource dataSource() {
     *     return DataSourceBuilder.create().build();
     * }
     */
    @Bean(name = "mainDataSource")
    @Primary
    public DataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "mainJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("mainDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "mainEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("mainDataSource") DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean emf = builder
                .dataSource(dataSource)
                .packages("com.joutvhu.training.rest.model.entity")
                .persistenceUnit("mainEntityManagerFactory")
                .build();

        return emf;
    }

    @Bean(name = "mainEntityManager")
    @Primary
    public EntityManager entityManager(
            @Qualifier("mainEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory
    ) {
        return entityManagerFactory.getNativeEntityManagerFactory().createEntityManager();
    }

    @Primary
    @Bean(name = "mainTransactionManager")
    public JpaTransactionManager transactionManager(
            @Qualifier("mainEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
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

    @Bean(name = "mainDataSourceInitializer")
    @ConditionalOnProperty(name = "spring.datasource.main.driverClassName", havingValue = "org.h2.Driver")
    public DataSourceInitializer mainDataSourceInitializer(
            @Qualifier("mainDataSource") DataSource dataSource
    ) {
        return loadScripts(dataSource, "query/schema.sql", "query/data.sql");
    }
}
