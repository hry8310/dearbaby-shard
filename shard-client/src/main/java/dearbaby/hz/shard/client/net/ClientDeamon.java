package dearbaby.hz.shard.client.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import dearbaby.hz.shard.client.bean.NetRspPack;
import dearbaby.hz.shard.client.bean.SlaveStatus;
import dearbaby.hz.shard.client.bean.Viewer;
import dearbaby.hz.shard.client.common.IoUtils;
import dearbaby.hz.shard.client.common.Utils;

public class ClientDeamon extends Thread {
	 private boolean isStop = false;
	 private Selector selector = null;
	 
	 private NetClient netClient;

	    
	 private SocketChannel channel = null;
	 
	 public ClientDeamon(NetClient cl){
		 netClient=cl;
	 }
	 
	 public void shutdown(boolean flag) {
	    this.isStop = true;
	 }
	 
	 
	 public boolean connect() {
	        try {
	            channel = SocketChannel.open(new InetSocketAddress(netClient.getAddress(), netClient.getPort()));
	            channel.configureBlocking(false);
	            selector = Selector.open();
	            
	            channel.register(selector, SelectionKey.OP_READ
	                    | SelectionKey.OP_WRITE);

	            
	        } catch (IOException e) {
	            e.printStackTrace();
	            return true;
	        }
	        

	        return false;
	 }
	 
	 
	 @Override
	    public void run() {
		 
		    while(connect()){
		    	try {
		    		sleep(netClient.getConnectTime());
		    	}catch(Exception e){
		    		
		    	}
		    }
	        System.out.println("client run..");
	        Viewer.setNetStatus(Viewer.netStatusOk);
	        while (this.isStop==false) {
	           
	            int num = 0;
	            try {
	                
	             
	                num = this.selector.select();

//	               
	            } catch (IOException e) {
	                
	                break;
	            }

	            if (num > 0) {
	                 
	                Iterator<SelectionKey> keys = selector.selectedKeys()
	                        .iterator();
	                while (keys.hasNext()) {
	                    SelectionKey key = keys.next();
	                   
	                    keys.remove();
	                    if (key.isReadable()) {
	                        
	                            try {
	                                NetRspPack pack=IoUtils.readLv((SocketChannel) key.channel(),netClient.getReadTimeout());
		                            if(pack.getStatus()==pack.STATUS_OK){
		                            	SlaveStatus status=Utils.analysisPack(pack);
		                                Viewer.setStatus(status);
		                            }else{
		                            	isStop=true;
		                            }
	                                
	                            } catch (Exception e) {
	                            // TODO Auto-generated catch block
	                                 e.printStackTrace();
	                             }
	                      
	                    } else if (key.isWritable()) {
	                        //do nothing
	                    }
	                }
	            }
	            

	        }

	        if (this.channel != null && this.channel.isOpen()) {
	            try {
	                this.channel.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }

	        if (this.selector != null && this.selector.isOpen()) {
	            try {
	                this.selector.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }

	        Viewer.setNetStatus(Viewer.netStatusErr);
	        netClient.reStart();
	    }

	 
}
