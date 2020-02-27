package ru.airlightvt.onlinerecognition.db.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan("ru.airlightvt.onlinerecognition.security")
public class FlywayConfiguration {
    @Bean
    public BeanPostProcessor postProcessFlyway(ApplicationContext context) {
        return new FlywayBeanPostProcessor(context);
    }

    @Bean
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("filesystem:/path/to/migrations/");
        flyway.setDataSource(dataSource());
        return flyway;
    }

    @Bean
    DataSource dataSource() {
        DataSource dataSource = new PGSimpleDataSource();
// data source configuration
        return dataSource;
    }

}
