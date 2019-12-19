package dearbaby.hz.shard.view.net;

import java.nio.channels.SocketChannel;
import java.util.concurrent.CopyOnWriteArrayList;

import dearbaby.hz.shard.view.common.ViewConfig;
import dearbaby.hz.shard.view.task.NetTask;
import dearbaby.hz.shard.view.task.TaskManager;

public class NetManager {

    private CopyOnWriteArrayList <SocketChannel> clientChannels ;
    
    public NetManager(){
    	clientChannels= new CopyOnWriteArrayList <SocketChannel> ();
    	
    	
    }
    
    public void start(TaskManager tm){
    	ViewServer vserver=new ViewServer();
    	AdminServer admServer =new AdminServer();
    	admServer.buildServer();
    	vserver.buildServer(ViewConfig.viewPort);
    	vserver.setClientChannels(clientChannels);
    	NetTask netTask=new NetTask();
    	netTask.setClientChannels(clientChannels);
    	netTask.setNetMsgList(tm.getNetMsgList());
    	vserver.start();
    	netTask.start();
    	
    }

}
