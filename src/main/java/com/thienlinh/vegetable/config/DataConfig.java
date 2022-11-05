package com.thienlinh.vegetable.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

@Configuration
@PropertySource("app.properties")
public class DataConfig {
    private final Environment env;

    public DataConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean()
    {
        Resource config=new ClassPathResource("hibernate.cfg.xml");
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setConfigLocation(config);
        sessionFactoryBean.setPackagesToScan(env.getProperty("vegetable.entity.package"));
        sessionFactoryBean.setDataSource(dataSource());

        return sessionFactoryBean;
    }
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(env.getProperty("vegetable.db.name"));
        dataSource.setUrl(env.getProperty("vegetable.db.url"));
        dataSource.setUsername(env.getProperty("vegetable.db.username"));
        dataSource.setPassword(env.getProperty("vegetable.db.password"));

        return dataSource;
    }
}

