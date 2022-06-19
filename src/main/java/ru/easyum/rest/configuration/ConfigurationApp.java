package ru.easyum.rest.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan(value = "ru.easyum.rest")
@EnableWebMvc
@EnableTransactionManagement
public class ConfigurationApp {


    @Bean
    public ComboPooledDataSource dataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/rest"); // создать бд
            dataSource.setDriverClass("org.postgresql.Driver");
            dataSource.setPassword("almasuj1998");
            dataSource.setUser("postgres");
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        return dataSource;
    }

    @Bean
    public  LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("ru.easyum.rest.entity");
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto","create");
        hibernateProperties.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQL94Dialect");
        hibernateProperties.setProperty("hibernate.show_sql","true");
        hibernateProperties.setProperty("hibernate.format_sql","true");
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

}
