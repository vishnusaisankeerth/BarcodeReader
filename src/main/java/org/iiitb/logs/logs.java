package org.iiitb.logs;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

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
                RollingFileAppender appender =   new RollingFileAppender(new PatternLayout("%d{dd/MMM/YYYY:HH:mm:ss }  %m%n"), "/tmp/input.out", true);
        

                appender.setMaxBackupIndex(5);
                appender.setMaxFileSize("1MB");
                Logger logger = Logger.getRootLogger();
                logger.setLevel(Level.DEBUG);
                //logger.setAdditivity(false);
                logger.addAppender(appender);
                return logger;
        }
        public static void removeDuplicates() throws IOException { 
                // PrintWriter object for output.txt 
                PrintWriter pw = new PrintWriter("/tmp/output.out"); 
                  
                // BufferedReader object for input.txt 
                BufferedReader br1 = new BufferedReader(new FileReader("/tmp/input.out")); 
                  
                String line1 = br1.readLine(); 
                  
                // loop for each line of input.txt 
                while(line1 != null) 
                { 
                    boolean flag = false; 
                      
                    // BufferedReader object for output.txt 
                    BufferedReader br2 = new BufferedReader(new FileReader("/tmp/output.out")); 
                      
                    String line2 = br2.readLine(); 
                      
                    // loop for each line of output.txt 
                    while(line2 != null) 
                    { 
                          
                        if(line1.equals(line2)) 
                        { 
                            flag = true; 
                            break; 
                        } 
                          
                        line2 = br2.readLine(); 
                      
                    } 
                      
                    // if flag = false 
                    // write line of input.txt to output.txt 
                    if(!flag){ 
                        pw.println(line1); 
                          
                        // flushing is important here 
                        pw.flush(); 
                    } 
                      
                    line1 = br1.readLine(); 
                      
                } 
                  
                // closing resources 
                br1.close(); 
                pw.close(); 
                  
                System.out.println("File operation performed successfully"); 
            } 
        }