package com.credit_suisse.app;
import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.credit_suisse.app.config.SpringRootConfig;

@ComponentScan
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Import( {SpringRootConfig.class} )
public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }
    
	@PostConstruct
	public void init(){
	}
}
