package dearbaby.hz.shard.view.common;

import java.io.IOException;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
public class IoUtils {
	public static SocketChannel accept(Selector selector,
            ServerSocketChannel serverChannel) {
        SocketChannel channel = null;
        try {
            channel = serverChannel.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
         } catch (Exception e) {
            
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return channel;
    }

	public static void read(Selector selector, SelectionKey selectionkey,
            List<SocketChannel> clientChannels) throws IOException {
        SocketChannel socketClientChannel = (SocketChannel) selectionkey
                .channel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(6 * 1024);
        StringBuilder content = new StringBuilder();
        int num = 0;
        try {
            
            while (socketClientChannel.read(buffer) > 0) {
                buffer.flip();
                 
            }

           
        } catch (IOException e) {
           
            num = -1;
        }
        
        if(num<0) {
            
            try {
                socketClientChannel.close();
                int length = clientChannels.size();
                for (int index = 0; index < length; index++) {
                    if (clientChannels.get(index).equals(socketClientChannel)) {
                       
                        clientChannels.remove(index);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
	}
	
	public static ByteBuffer getBuffer(String str){
		byte[] bs= str.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bs.length);
        writeBuffer.put(bs);
        writeBuffer.flip();
        return writeBuffer;

 
	} 
	
	public static ByteBuffer getLvBuffer(String str){
		byte[] bs= str.getBytes(); 
		ByteBuffer bbf = ByteBuffer.allocate(bs.length + 4);
		bbf.putInt(bs.length);
		bbf.put(bs);
		bbf.flip(); 
		return bbf;
	}
	
	public static void write(List<SocketChannel> clientChannels,String str){
		  try {
			  List<Integer> idxs=new ArrayList<Integer>(); 
			  for (SocketChannel c : clientChannels) {
				  if(!c.isConnected()||!c.isOpen()){
					  c.close();
					  clientChannels.remove(c);
					  continue;
				  }
				  //add end with flag $
				  c.write(getLvBuffer(str+"$"));
                   
              }
		  } catch (IOException e) {
              e.printStackTrace();
          }
	}

}
