package dearbaby.hz.shard.view.task;

import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

import dearbaby.hz.shard.view.bean.MasterMsg;
import dearbaby.hz.shard.view.bean.NetMsg;
import dearbaby.hz.shard.view.bean.SlaveMsg;
import dearbaby.hz.shard.view.common.IoUtils;
import dearbaby.hz.shard.view.common.Utils;
import dearbaby.hz.shard.view.db.DbHandle;

public 	  class NetTask extends Thread {

	 private CopyOnWriteArrayList <SocketChannel> clientChannels ;
	  
     private LinkedBlockingQueue<NetMsg> netMsgList;
     

 	 protected boolean isStop=false;

 
     public void run(){
    	 while(true){
 			
 			try{
 				
 				 
 			    if(isStop==true){
 			    	break;
 			    }
 			    
 			    NetMsg nmsg=netMsgList.take();
 			    IoUtils.write(clientChannels ,Utils.netMsg2Str(nmsg));
 			}catch(Exception e){
 				e.getStackTrace();
 			}
 			
 		}	
     }


	public CopyOnWriteArrayList<SocketChannel> getClientChannels() {
		return clientChannels;
	}


	public void setClientChannels(CopyOnWriteArrayList<SocketChannel> clientChannels) {
		this.clientChannels = clientChannels;
	}


	public LinkedBlockingQueue<NetMsg> getNetMsgList() {
		return netMsgList;
	}


	public void setNetMsgList(LinkedBlockingQueue<NetMsg> netMsgList) {
		this.netMsgList = netMsgList;
	}


	  


	public void shutdown() {
		this.isStop = true;
	}
     
     
     
	
}
