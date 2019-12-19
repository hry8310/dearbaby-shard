package dearbaby.hz.shard.view.common;

import java.util.Properties;

public class ViewConfig {

	public static long interval;
	public static int viewPort;
	public static int adminPort;
	
	static{
		interval=1000l;
		viewPort=7060;
		adminPort=7070;
	}
	
	public static void load(Properties p){
		String _interval=p.getProperty("view.interval");
		if(_interval!=null){
			interval=Long.valueOf(_interval);
		}
		
		String _port=p.getProperty("view.port");
		if(_port!=null){
			viewPort=Integer.valueOf(_port);
		}
		
		String _admPort=p.getProperty("view.adm.port");
		if(_admPort!=null){
			adminPort=Integer.valueOf(_admPort);
		}
	}
	
}
