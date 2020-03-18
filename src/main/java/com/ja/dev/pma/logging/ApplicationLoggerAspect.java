package com.ja.dev.pma.logging;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ApplicationLoggerAspect {
	
	private final Logger LOGGER =  LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Pointcut - where logging should happen
	 * In Controller package - all classes, 
	 */
	@Pointcut("within(com.ja.dev.pma.controllers..*)" 
			+ "|| within(com.ja.dev.pma.services..*)")
	public void definePackagePointcuts() {
		// empty method used to identify the location of object in the pointcut
	}
	
	/**
	 * @After - Advice takes place before method call
	 */
	@After("definePackagePointcuts()")
	public void log() {
		LOGGER.debug("---#################################---");
	}

}
