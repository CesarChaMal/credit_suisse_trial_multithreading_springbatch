package com.credit_suisse.app.core;

import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.credit_suisse.app.dao.InstrumentPriceModifierDao;
import com.credit_suisse.app.util.CommonConstants;

public class CalculatorEngineRefresh extends TimerTask {
	
	private volatile static CalculatorEngineRefresh INSTANCE;
	
	private volatile CalculatorEngine calculatorEngine;
	
	private CalculatorEngineRefresh() {
		calculatorEngine = new CalculatorEngine();
	}

	private static final Logger logger = LoggerFactory.getLogger(CalculatorEngineRefresh.class);

	@Override
	public void run() {
		logger.info("Started CalculatorEngine refreshing");
		reloadCalculatorEngine();
	}

	public static CalculatorEngineRefresh getInstance() {
		if (INSTANCE == null) {
			synchronized (CalculatorEngineRefresh.class) {
				if (INSTANCE == null) {
					INSTANCE = new CalculatorEngineRefresh();
				}
			}
		}
		return INSTANCE;
	}

	public void reloadCalculatorEngine(){
		ThreadManager cachemanager = new ThreadManager();
		
		Thread t[] = new Thread[1];
		t[0] = new CalculatorEngine();
		cachemanager.setTask(t);
		cachemanager.start();
	}
}
