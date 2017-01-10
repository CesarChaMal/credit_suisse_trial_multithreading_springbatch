package com.credit_suisse.app.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.credit_suisse.app.config.db.HsqlDataSource;
import com.credit_suisse.app.core.TaskManager;
import com.credit_suisse.app.dao.InstrumentPriceModifierDao;
import com.credit_suisse.app.util.CommonConstants;

@Configuration
@Import( {PropertyConfig.class, HsqlDataSource.class} )
public class SpringRootConfig {

	private static final Logger logger = LoggerFactory.getLogger(SpringRootConfig.class);

	@Autowired
	DataSource dataSource;

	@Bean
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}	

	private InstrumentPriceModifierDao instrumentPriceModifierDao;


	@Value("${ThreadPoolSize}")
    private int threadPoolSize;
    
    @Bean
    public Bootstrap bootstrap() {
        return new Bootstrap();
    }
    
    public TaskManager taskManager() {
		System.out.println("instrumentPriceModifierDao: " + instrumentPriceModifierDao);
        return new TaskManager();
    }
    
    @Bean
    public ExecutorService executorService() {
    	CommonConstants.THREAD_POOL_SIZE = threadPoolSize;
        return Executors.newFixedThreadPool(threadPoolSize);
    }
    
	@PostConstruct
	public void init() {
	}
	
}