package org.iiitb.logs;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import java.net.URL;



public class logs {
	public static Logger logger;
	public logs()
	{
		
		try {
			logger=getlogger();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static Logger getlogger() throws IOException  {
		RollingFileAppender appender =   new RollingFileAppender(new PatternLayout("%d{dd/MMM/YYYY:HH:mm:ss }  %m%n"), "/tmp/logs.out", true);
	

		appender.setMaxBackupIndex(5);
		appender.setMaxFileSize("1MB");
		Logger logger = Logger.getRootLogger();
		logger.setLevel(Level.DEBUG);
		logger.addAppender(appender);
		return logger;
	}
	
}