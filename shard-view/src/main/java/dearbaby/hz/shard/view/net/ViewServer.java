package dearbaby.hz.shard.view.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import dearbaby.hz.shard.view.common.IoUtils;

public class ViewServer  extends Thread{
	
	private boolean isStop=false;
	
	private ServerSocketChannel serverChannel = null;
    private Selector selector = null;
     
    private CopyOnWriteArrayList <SocketChannel> clientChannels ;
    
    
    public void buildServer(int port) {
        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.socket().bind(new InetSocketAddress(port));
            selector = Selector.open();
            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            
            System.out.println("Server is listening now...");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void run() { 
      while (isStop==false) {
          int num = 0;
          try {
              num = selector.select();
          } catch (IOException e) {
              System.out.println("Error while select channel:" + e);
          }
          if (num > 0) {
              Iterator<SelectionKey> it = selector.selectedKeys().iterator();
              while (it.hasNext()) {
                  SelectionKey key = it.next();
                  it.remove();
                  if (key.isAcceptable()) {
                      this.clientChannels.add(IoUtils.accept(selector,
                              serverChannel));
                  } else if (key.isReadable()) {
                      
                      try {
                    	  IoUtils.read(selector, key, clientChannels );
                      } catch (IOException e) {
                          
                          e.printStackTrace();
                      }
                  }
              }
          }
          try {
              Thread.sleep(500);
          } catch (InterruptedException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
      }
      System.out.println("server to close..");
      if (this.serverChannel != null && this.serverChannel.isOpen()) {
          try {
              this.serverChannel.close();
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
   }
	public CopyOnWriteArrayList<SocketChannel> getClientChannels() {
		return clientChannels;
	}
	public void setClientChannels(CopyOnWriteArrayList<SocketChannel> clientChannels) {
		this.clientChannels = clientChannels;
	}
    
    
    
}
