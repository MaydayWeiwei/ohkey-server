package com.ohkey.app.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@EnableJpaRepositories("com.ohkey.app.repository")
@ComponentScan(basePackages={"com.ohkey.app.service"})
/*We have to declare Service Pakcages As component scan here 
 * because the UserServiceDetail will be used nextly in the Spring Security Configuration
 *  
 *  These are serverals packages where they are declared:
 *  
 *  model: in the DBConfig detail
 *  repository: in the DBConfig declaration 
 *  service: in the DBConfig declaration
 * 
 * 	controlers: in the mvcconfig declaration. It will be initialized secondly because
 * mvcConfig is initialized togother with dispacther servlet
 * */
public class DBConfig {

	@Autowired
	private Environment env;

	@Bean(name="dataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		em.setJpaVendorAdapter(vendorAdapter);
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.ohkey.app.model" });
		em.setJpaProperties(additionalProperties());

		return em;
	}


	@Bean(name="transactionManager")
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	/*
     Post-processor to perform exception translation on @Repository classes
     (from native exceptions such as JPA PersistenceExceptions to
     Spring DataAccessException hierarchy).*/
 
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql","true");
		return properties;
	}
}