package com.credit_suisse.app.core;

import java.net.InetAddress;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.credit_suisse.app.dao.InstrumentPriceModifierDao;
import com.credit_suisse.app.util.CommonConstants;
import com.credit_suisse.app.util.emWorkerProfile;

@Service("taskManager")
@DependsOn("bootstrap")
public class TaskManager implements InitializingBean, DisposableBean, ApplicationListener<ApplicationEvent> {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskManager.class);
	
	private ExecutorService executorService;

	public int numThreads = CommonConstants.MAX_THREADS;
    
    Timer timer;

	public TaskManager() {
		System.out.println("Task Manager");
//		System.out.println("instrumentPriceModifierDao: " + instrumentPriceModifierDao);
	}
	
	public TaskManager(int threads) {
	    this.numThreads = threads;
	}

	@Autowired(required = true)
	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	public ExecutorService getExecutorService() {
		return this.executorService;
	}

	private void start() {
	    logger.info("Starting TaskManager with " + numThreads + " threads...");
		executorService = Executors.newFixedThreadPool(numThreads);
	    
//		System.out.println("instrumentPriceModifierDao: " + instrumentPriceModifierDao);
		
		String hostname = null;
		try{
			hostname = InetAddress.getLocalHost().getHostName();
		}catch(Exception e){
			logger.error("Unable to get hostname",e);
		}

//		executorService.execute(new TaskExecutor("TaskExecutor_" +  hostname + "_" + CommonConstants.WORKER_PROFILE, emWorkerProfile.PROFILE_INSTRUMENT, instrumentPriceModifierDao));
		executorService.execute(new TaskExecutor("TaskExecutor_" +  hostname + "_" + CommonConstants.WORKER_PROFILE, emWorkerProfile.PROFILE_INSTRUMENT));
	}

    public void afterPropertiesSet() throws Exception {
        logger.info("Initializing TaskManager...");
    }

    public void destroy() {
        logger.info("Destroying TaskManager...");

    }
    
    public void onApplicationEvent(ApplicationEvent event) 
    {
        logger.debug("ApplicationEvent::" + event.getClass().getName());

        if (event instanceof ContextRefreshedEvent) 
        {
        	logger.info("TaskManager received ContextRefreshedEvent");

//        	System.out.println("instrumentPriceModifierDao: " + instrumentPriceModifierDao);
            
	        start();
	        
	        timer = new Timer();
	        
	        logger.debug("STARTING Threads Managers");
	        
	        if(CommonConstants.MANAGER_ON)
//	        	timer.schedule(CalculatorEngineRefresh.getInstance(instrumentPriceModifierDao),0, CommonConstants.REFRESH_MILLIS);
	        	timer.schedule(CalculatorEngineRefresh.getInstance(),0, CommonConstants.REFRESH_MILLIS);
	        else
	        	logger.debug("Instrument Manager is configured not to run, skipping startup!");
        }
    }

}
