package cc.pogoda.backend.config;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import cc.pogoda.backend.dao.SummaryDao;
import jakarta.persistence.EntityManagerFactory;


@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"cc.pogoda.backend.dao.repository"})
@ComponentScan("cc.pogoda.backend")
public class Config {
	
	@Bean
	public SummaryDao summaryDao() {
		return new SummaryDao();
	}
	
	@Bean
	public DriverManagerDataSource dataSource() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		DriverManagerDataSource out = new DriverManagerDataSource();
		out.setUrl("jdbc:mysql://localhost:3306/aprs2rrd?useSSL=false&serverTimezone=UTC");
		out.setDriverClassName("com.mysql.cj.jdbc.Driver");
		out.setUsername("aprs2rrd");
		out.setPassword("qwertyuiop");
		return out;
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean out = new LocalContainerEntityManagerFactoryBean();
		
		HashMap<String, String> jpaProperties = new HashMap<String, String>();
		//jpaProperties.put("hibernate.physical_naming_strategy", "cc.pogoda.backend.dao.aux.MyNamingStrategy");
		jpaProperties.put("hibernate.jdbc.time_zone", "UTC");
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(true);
		//vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		// org.hibernate.cj.dialect.MySQLDialect
		vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		
		out.setJpaVendorAdapter(vendorAdapter);
		out.setJpaPropertyMap(jpaProperties);

		
		out.setPackagesToScan("cc.pogoda.backend.types.model");
		out.setDataSource(dataSource());
		
		return out;
		
	}
	
   @Bean
   public PlatformTransactionManager transactionManager(
     EntityManagerFactory emf){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(emf);
 
       return transactionManager;
   }
}
