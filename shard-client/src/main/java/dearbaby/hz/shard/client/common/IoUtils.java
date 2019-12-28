package dearbaby.hz.shard.client.common;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import dearbaby.hz.shard.client.bean.NetRspPack;

public class IoUtils {

	/*
	 * no-use
	 */
	public static String read(SocketChannel channel) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocateDirect( 1024);
        int num = 0;
        String content = "";
        while ((num = channel.read(buffer)) > 0) {
            buffer.flip();
          
            byte[] dst=new byte[num];
            buffer.get(dst);
            content += new String(dst);
           
        }
        
        if(content.length()>0)System.out.println(content);
        return content;
    }
	
	/*
	 * no-use
	 */
	public static String readLv(SocketChannel channel) throws IOException {

		ByteBuffer buffer = ByteBuffer.allocate(4);
		while (true) {
			channel.read(buffer);

			if (buffer.remaining() <= 0) {
				break;
			}
		}
		buffer.flip();
		int len = buffer.getInt();
		ByteBuffer bf = ByteBuffer.allocate(len);

		while (true) {
			channel.read(bf);
			if (bf.remaining() <= 0) {
				break;
			}
		}

        String content=new String(bf.array());
        
        return content;
    }
	
	public static NetRspPack readLv(SocketChannel channel,long time) throws Exception {

		NetRspPack pack=new NetRspPack();
		ByteBuffer buffer = ByteBuffer.allocate(4);
		int k=0;
		while (true) {
			channel.read(buffer);

			if (buffer.remaining() <= 0) {
				break;
			}
			if (k>=5) {
				pack.setStatus(pack.STATUS_ERR);
				return pack;
				//break;
			}
			Thread.sleep(time/10);
			k++;
			
		}
		buffer.flip();
		int len = buffer.getInt();
		ByteBuffer bf = ByteBuffer.allocate(len);
        k=0;
		while (true) {
			channel.read(bf);
			if (bf.remaining() <= 0) {
				break;
				
			}
			if (k>=5) {
				
				pack.setStatus(pack.STATUS_ERR);
				return pack;
			}
			Thread.sleep(time/10);
			k++;
		}

        String content=new String(bf.array());
        if(!content.endsWith("$")){
        	pack.setStatus(pack.STATUS_ERR);
			return pack;
        }
        System.out.println(content);
        pack.setStatus(pack.STATUS_OK);
		pack.setContent(content);
         
        return pack;
    }
}
