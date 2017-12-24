package com.plugins.collector.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "sqliteEntityManagerFactory",
        transactionManagerRef = "sqliteTransactionManager",
        basePackages = {"com.plugins.collector.dao.sqlite"}
)
public class SqliteDataSourceConfiguration {
    @Autowired
    @Qualifier("sqliteDataSource")
    private DataSource sqliteDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name = "sqliteEntityManager")
    public EntityManager sqliteEntityManager(EntityManagerFactoryBuilder builder) {
        return sqliteEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "sqliteEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sqliteEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(sqliteDataSource)
                .properties(getVendorProperties(sqliteDataSource))
                .packages("com.plugins.collector.dao.sqlite")
                .persistenceUnit("sqlitePersistenceUnit")
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Primary
    @Bean(name = "sqliteTransactionManager")
    public PlatformTransactionManager sqliteTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(sqliteEntityManagerFactory(builder).getObject());
    }
}
