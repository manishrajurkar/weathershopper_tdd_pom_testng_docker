package com.automation.develop.practice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class checkLog4j {
    public static Logger logger = LogManager.getLogger(checkLog4j.class.getName());
    public static void main(String[] args) {
        System.setProperty("log4j.configurationFile", "./resources/properties/log4j2.properties");

        // logger = LogManager.getLogger(this);  //(BaseClass.class)
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        logger.fatal("This is a fatal message");
    }
}
