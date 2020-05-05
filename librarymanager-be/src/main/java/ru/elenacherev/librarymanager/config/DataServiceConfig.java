package ru.elenacherev.librarymanager.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("ru.elenacherev.librarymanager")
@EnableJpaRepositories
@EnableSpringDataWebSupport
public class DataServiceConfig {

    // Логирование ошибок
    private static Logger logger = LoggerFactory.getLogger(DataServiceConfig.class);

    @Bean
    public DataSource dataSource() {
        try {
            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
            return dbBuilder.setType(EmbeddedDatabaseType.H2) // установили тип БД H2
                    .build(); // Создали источник данных
        } catch (Exception ex) {
            logger.error("Embedded DataSource bean cannot be created!", ex);
            return null;
        }
    }

    // Свойства Hibernate
    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();

        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProp.put("hibernate.hbm2ddl.auto", "create-drop");
        hibernateProp.put("hibernate.show_sql", "true"); // Показывать sql-код в консоли, удобно для отладки
        hibernateProp.put("hibernate.hbm2ddl.auto", "none"); // сгеннерировать БД из классов сущностей и обновлять её, если стрктура классов-сущностей поменняется
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);

        return hibernateProp;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {

        try {
            return new HibernateJpaVendorAdapter();
        } catch (Exception ex) {
            logger.error("HibernateJpaVendorAdapter bean cannot be created!", ex);
            return null;
        }
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(
            DataSource dataSource,
            Properties hibernateProperties,
            JpaVendorAdapter jpaVendorAdapter
    ) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("ru.elenacherev.librarymanager.domain.entities");
        factoryBean.setDataSource(dataSource);

        factoryBean.setJpaProperties(hibernateProperties);
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
