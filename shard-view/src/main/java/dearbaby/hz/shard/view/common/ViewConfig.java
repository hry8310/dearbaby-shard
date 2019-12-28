package dearbaby.hz.shard.view.common;

import java.util.Properties;

public class ViewConfig {

	public static long interval;

	public static long allowLate;
	public static int viewPort;
	public static int adminPort;
	
	public static String handleClass;
	public static String handleParam;
	
	
	static{
		allowLate=200l;
		interval=1000l;
		viewPort=7060;
		adminPort=7070;
	}
	
	public static void load(Properties p){
		 
		
		interval=loadLong(p,"view.interval",interval);
				
		allowLate=loadLong(p,"view.allowLate",allowLate);
		
		viewPort=loadInt(p,"view.port",viewPort);
				 

		adminPort=loadInt(p,"view.adm.port",adminPort);
		

		  
		
	    handleClass=p.getProperty("view.handleClass");
		 

	    handleParam=p.getProperty("view.handleParam");
	}
	
	
	private static long loadLong(Properties p,String name,long def){
		String namestr=p.getProperty(name);
		if(namestr!=null){
			try{
		         return Long.valueOf(namestr);
			}catch(Exception e){
				return def;
			}
		}
		return def;
	}
	
	private static int loadInt(Properties p,String name,int def){
		String namestr=p.getProperty(name);
		if(namestr!=null){
			try{
		         return Integer.valueOf(namestr);
			}catch(Exception e){
				return def;
			}
		}
		return def;
	}
}
