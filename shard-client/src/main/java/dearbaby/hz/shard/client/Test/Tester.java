package dearbaby.hz.shard.client.Test;

import dearbaby.hz.shard.client.net.NetClient;

public class Tester {
	 public static void main( String[] args )
	 {
		 NetClient client =new NetClient();
		 client.setAddress("127.0.0.1");
		 client.setPort(7060);
		 
		 client.start();
	 }
}
