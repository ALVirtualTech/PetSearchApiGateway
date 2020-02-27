package ru.airlightvt.onlinerecognition.db.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.internal.dbsupport.DbSupport;
import org.flywaydb.core.internal.dbsupport.h2.H2DbSupport;
import org.flywaydb.core.internal.dbsupport.postgresql.PostgreSQLDbSupport;
import org.flywaydb.core.internal.resolver.sql.SqlMigrationResolver;
import org.flywaydb.core.internal.util.Locations;
import org.flywaydb.core.internal.util.PlaceholderReplacer;
import org.flywaydb.core.internal.util.scanner.Scanner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class FlywayBeanPostProcessor implements BeanPostProcessor {
    private final ApplicationContext context;

    public FlywayBeanPostProcessor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if (o instanceof Flyway) {
            Flyway flyway = (Flyway) o;
            flyway.setSkipDefaultResolvers(true);
            ApplicationContextAwareSpringJdbcMigrationResolver resolver = new ApplicationContextAwareSpringJdbcMigrationResolver(
                    new Scanner(Thread.currentThread().getContextClassLoader()),
                    new Locations("classpath:ru/airlightvt/onlinerecognition/db"),
                    context.getBean(org.flywaydb.core.api.configuration.FlywayConfiguration.class),
                    context);
            SqlMigrationResolver sqlMigrationResolver = null;
            try {
                sqlMigrationResolver = new SqlMigrationResolver(
                        getDbSupport(),
                        new Scanner(Thread.currentThread().getContextClassLoader()),
                        new Locations("classpath:ru/airlightvt/onlinerecognition/db"),
                        PlaceholderReplacer.NO_PLACEHOLDERS,
                        flyway);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            flyway.setResolvers(sqlMigrationResolver, resolver);
        }
        return o;
    }

    private DbSupport getDbSupport() throws SQLException {
        DataSource dataSource = context.getBean(DataSource.class);
        if( ((org.apache.tomcat.jdbc.pool.DataSource)dataSource).getDriverClassName().equals("org.h2.Driver"))
        {
            return new H2DbSupport(dataSource.getConnection());
        }
        else
        {
            return new PostgreSQLDbSupport(dataSource.getConnection());
        }
    }
};
}
