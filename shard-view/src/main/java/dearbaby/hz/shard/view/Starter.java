package dearbaby.hz.shard.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dearbaby.hz.shard.view.common.ViewConfig;
import dearbaby.hz.shard.view.db.DataSourceConfig;
import dearbaby.hz.shard.view.net.NetManager;
import dearbaby.hz.shard.view.task.TaskManager;

/**
 * Hello world!
 *
 */
public class Starter 
{
	private static final Logger logger = LoggerFactory.getLogger(Starter.class);
	
    public static void main( String[] args )
    {
    	start(args);
    	System.out.println("eeefff   "+args.length);
    	 
    }
    
    private static void start(String[] args){
    	try{
    		
    	    CommandLineParser parser = new BasicParser( );  
       	    Options options = new Options( );  
       	    options.addOption("c", "config file", true, "config file");  
       	    CommandLine commandLine = parser.parse( options, args );  
       	     // Set the appropriate variables based on supplied options  
       	    boolean verbose = false;  
       	    String file = "";  
       	    
       	 
       	    if( commandLine.hasOption('c') ) {  
       	       file = commandLine.getOptionValue('c');  
       	    }  
       	    
       	   // System.out.println("XXXXXXXCCC  " +file);
       	    
       	    DataSourceConfig.properties = new Properties();
       	    InputStream in =new FileInputStream(new File(file));
       	    logger.info("deesssxxxx");
       	    DataSourceConfig.properties.load(in);
       	    ViewConfig.load(DataSourceConfig.properties);
       	    DataSourceConfig.buildDataSource(DataSourceConfig.properties);
       	    
       	    TaskManager tm=new TaskManager();
       	  
       	  
       	    
       	    NetManager nm=new NetManager();
       	    nm.start(tm);
       	 
       	    tm.start();
       	    ViewApp.setTaskMamager(tm);
       	    ViewApp.setNetMamager(nm);
       	    
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	
    }
    
    
    
    
}
