package dearbaby.hz.shard.client.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class NetClient {
	 
    private Selector selector = null;

    
    private SocketChannel channel = null;
    
    private String address;
    
    private int port;
 
    private long readTimeout=10000l;
   

    private long connectTime=2000l;
    
    


	public void start(){
    	ClientDeamon deamon=new ClientDeamon(this);
    	deamon.start();
    }
	
	public void reStart(){
	 
		start();
	}
    
    public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public int getPort() {
		return port;
	}



	public void setPort(int port) {
		this.port = port;
	}


    
    
    
     


	public Selector getSelector() {
		return selector;
	}



	public void setSelector(Selector selector) {
		this.selector = selector;
	}



	public SocketChannel getChannel() {
		return channel;
	}



	public void setChannel(SocketChannel channel) {
		this.channel = channel;
	}



	public long getReadTimeout() {
		return readTimeout;
	}



	public void setReadTimeout(long readTimeout) {
		this.readTimeout = readTimeout;
	}

	public long getConnectTime() {
		return connectTime;
	}

	public void setConnectTime(long connectTime) {
		this.connectTime = connectTime;
	}
    
	
    
}
