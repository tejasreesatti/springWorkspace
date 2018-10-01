package com.capgemini.bankapp.config;

import javax.sql.DataSource;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages= {"com.capgemini.bankapp"})
@PropertySource("classpath:db.properties")
public class AppConfig {

	

	@Value("${db.driverClassName}")
	private String driverClassName;
	@Value("${db.url}")
	private String dburl;
	@Value("${db.username}")
	private String username;
	@Value("${db.password}")
	private String password;
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource  dataSource = new DriverManagerDataSource();
	/*	dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/bankaccounts");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		*/
		
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(dburl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
		
	}
	
	@Bean
	public JdbcTemplate getJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate;
		
	}
	
}
