package com.credit_suisse.app.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadManager extends Thread {
	
	private static final Logger logger = LoggerFactory.getLogger(ThreadManager.class);

	Thread[] task = null;

	public ThreadManager(){
	}
	
	public Thread[] getTask() {
	    logger.debug("getTask() - returning " + task.length + " tasks");
	    return task;
	}

	public void setTask(Thread[] task) {
	    logger.debug("setTask() - " + task.length + " tasks");
	    this.task = task;
	}
	
	public void run(){
		for (int i=0; i < task.length; i++){
	        logger.debug("run() starting task " + i + " "+ task[i].getName());
		    task[i].start();
		}
	}
	
}
