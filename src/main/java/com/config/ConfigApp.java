package com.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.service.impl", "com.dao.impl", "com.controller", "com.exception_handler"})
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableScheduling
@Import(EmailConfig.class)
@PropertySource("classpath:db.properties")
@EnableJpaRepositories(basePackages = "com.repository")
public class ConfigApp implements WebMvcConfigurer, EnvironmentAware {
    private Environment env;

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
        JpaTransactionManager manager = new JpaTransactionManager();

        manager.setEntityManagerFactory(factory);
        manager.setDataSource(dataSource());

        return manager;
    }

    @Bean
     public DataSource dataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();

        String userName = env.getProperty("jdbc.username");
        String password = env.getProperty("jdbc.password");

        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUsername(userName);
        source.setPassword(password);
        source.setUrl("jdbc:mysql://localhost:3306/bulletin_jpa?serverTimezone=Europe/Berlin");

        return source;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
        adapter.setGenerateDdl(false);
        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();

        emfb.setDataSource(dataSource());
        emfb.setJpaVendorAdapter(jpaVendorAdapter());
        emfb.setPackagesToScan("com.domain");

        return emfb;

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

   /* @Bean
    public AdvertisementDAOImpl advertisementDAO(){
        return new AdvertisementDAOImpl();
    }

    @Bean
    public AdvertisementService advertisementService (AdvertisementDAOImpl advertisementDAO){
        return new AdvertisementServiceImpl(advertisementDAO);
    }*/



}
