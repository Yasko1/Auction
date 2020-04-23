package by.epam.lab.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("by.epam.lab")
@PropertySource("classpath:database2.properties")
public class AppConfig {

	@Value("${db.user}")
	private String username;
	@Value("${db.password}")
	private String password;
	
	@Autowired
    Environment environment;
	
	@Autowired
	DataSource dataSource;
    
    @Bean
    DataSource dataSource() {
    	DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(environment.getProperty("db.url"));
		driverManagerDataSource.setUsername(username);
		driverManagerDataSource.setPassword(password);
		driverManagerDataSource.setDriverClassName(environment.getProperty("db.driver"));
		System.out.println(username + " " + password);
		return driverManagerDataSource;
	}
    
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
        
    }                                                                                                                                                                                                                                                                                                                                                         
}
