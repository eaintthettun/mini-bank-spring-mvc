package com.mini.bank.init;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value= {"classpath:database.properties","classpath:hibernate.properties"})
@EnableTransactionManagement
public class HibernateInitializer {
	@Autowired
	private Environment environment;
	public HibernateInitializer() {
		System.out.println("Hibernate initializer starting");
	}
	
	@Bean
	public DataSource dataSource() {
		System.out.println("Data Source connection");
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName(this.environment.getProperty("jdbc.driver"));
		ds.setUrl(this.environment.getProperty("jdbc.url"));
		ds.setUsername(this.environment.getProperty("jdbc.user"));
		ds.setPassword(this.environment.getProperty("jdbc.password"));
		return ds;
	}
	
	private Properties hibernateProperties() {
		Properties prop=new Properties();
		prop.put("hibernate.dialect", this.environment.getProperty("hb.dialect"));
		prop.put("hibernate.show_sql", this.environment.getProperty("hb.sql.show"));
		prop.put("hibernate.format_sql", this.environment.getProperty("hb.sql.format"));
		return prop;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		System.out.println("SessionFactory creation for Hibernate framework");
		LocalSessionFactoryBean lsfb=new LocalSessionFactoryBean();
		lsfb.setDataSource(dataSource());
		lsfb.setHibernateProperties(hibernateProperties());
		lsfb.setPackagesToScan("com.mini.bank.entity");
		return lsfb;
	}
	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager htm=new HibernateTransactionManager();
		htm.setSessionFactory(sessionFactory().getObject());
		return htm;
	}
}

