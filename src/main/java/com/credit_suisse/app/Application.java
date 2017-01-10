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
    
//	@Autowired
//	DataSource dataSource;
//
//	@Bean
//	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
//		return new NamedParameterJdbcTemplate(dataSource);
//	}
//
//	@Bean
//	public JdbcTemplate getJdbcTemplate() {
//		return new JdbcTemplate(dataSource);
//	}	
//
//	@Autowired
//	InstrumentPriceModifierDao instrumentPriceModifierDao;
//
//    @Bean
//	public InstrumentPriceModifierDao getInstrumentPriceModifierDao() {
//		return instrumentPriceModifierDao;
//	}

	@PostConstruct
	public void init(){
//		List<InstrumentPriceModifier> modifiers =  new ArrayList<>();
//		modifiers.add(new InstrumentPriceModifier(1l, "INSTRUMENT1", 1.05)); 
//		modifiers.add(new InstrumentPriceModifier(2l, "INSTRUMENT2", 1.10)); 
//		modifiers.add(new InstrumentPriceModifier(3l, "INSTRUMENT3", 1.15)); 
//		modifiers.add(new InstrumentPriceModifier(4l, "INSTRUMENT5", 2.0));
//		
//		
//		System.out.println("instrumentPriceModifierDao: " + instrumentPriceModifierDao);
//		System.out.println("datasource: " + dataSource);
//		
//		instrumentPriceModifierDao.save(modifiers);
		
//		for (int i = 0; i < 5000; i++) {
//			System.out.println("instrumentPriceModifierDao: " + instrumentPriceModifierDao);
//		}
		
	}
    
}
