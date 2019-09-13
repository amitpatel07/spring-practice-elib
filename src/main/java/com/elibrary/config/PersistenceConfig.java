package com.elibrary.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

	@Value("${db.url}")
	private String dbUrl;

	@Value("${db.username}")
	private String dbUserName;
	@Value("${db.password}")
	private String dbPassword;
	@Value("${db.driver}")
	private String driver;
	@Value("${hibernate.dialect}")
	private String dialect;
	@Value("${hibernate.hbm2ddl}")
	private String hbm2dll;
	@Value("${hibernate.showsql}")
	private String showSql;
	@Value("${hibernate.formatsql}")
	private String formatSql;
	@Value("${hibernate.connection_strategy}")
	private String connectionStrategy;
	@Value("${hibernate.enable_lazy_load}")
	private boolean lazyLoad;

	@Bean
	public DataSource datasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUserName);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}
    @Bean
	public LocalContainerEntityManagerFactoryBean entityManager() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(datasource());
		factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		factory.setPackagesToScan("com.elibrary.data.model");

		//
		Properties jpaProperties = new Properties();
		jpaProperties.put(Environment.DIALECT, dialect);
		jpaProperties.put(Environment.GENERATE_STATISTICS, true);
		jpaProperties.put(Environment.HBM2DDL_AUTO, hbm2dll);

		jpaProperties.put(Environment.SHOW_SQL, showSql);

		jpaProperties.put(Environment.FORMAT_SQL, formatSql);

		jpaProperties.put(Environment.CONNECTION_HANDLING, connectionStrategy);
		jpaProperties.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, lazyLoad);
		factory.setJpaProperties(jpaProperties);

		return factory;
	}
    
    @Bean
    public JpaTransactionManager transactionManager() {
    	JpaTransactionManager transactionManager = new JpaTransactionManager();
    	transactionManager.setEntityManagerFactory(entityManager().getObject());
    	return transactionManager ;
    }
}
