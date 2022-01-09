package com.automation.develop.practice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLogs {

	//private static Logger logger = LogManager.getLogger(TestLogs.class);
	private static Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		
//		System.setProperty("log4j.configurationFile", "./src/resources/Properties/log4j2.properties");
//		log.error("Test Error");


		//private static Logger logger = LogManager.getLogger();
		//public void performSomeTask(){
			logger.debug("This is a debug message");
			logger.info("This is an info message");
			logger.warn("This is a warn message");
			logger.error("This is an error message");
			logger.fatal("This is a fatal message");

	}

}
