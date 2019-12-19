package dearbaby.hz.shard.view.net;
import com.alibaba.fastjson.JSON;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;



import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import java.util.Map;

import dearbaby.hz.shard.view.bean.MonitorStatus;
import dearbaby.hz.shard.view.common.Utils;
import dearbaby.hz.shard.view.common.ViewConfig;
import dearbaby.hz.shard.view.net.handle.MonitorHandler;

public class AdminServer {
	
	public void buildServer(){
	  try{
		  HttpServer server = HttpServer.create(new InetSocketAddress(ViewConfig.adminPort), 0);
          server.createContext("/monitor", new MonitorHandler());
          server.start();
       }catch(Exception e){
    	  e.printStackTrace();
       }
	        
	}
    
}


