package dearbaby.hz.shard.client.common;

import dearbaby.hz.shard.client.bean.MsCmd;

public class MsHelper {
	private static ThreadLocal<MsCmd> localMsCmd = new ThreadLocal<MsCmd>();
	
	public static void msPush(MsCmd c){
		localMsCmd.set(c);
	}
	
	public static MsCmd msget(){
		return localMsCmd.get();
	}
	
	
	public static void msUnload(){
		localMsCmd.remove();
	}
	
	public static void x(){
		msUnload();
	}
	
	public static void msPush(int t){
		MsCmd c=new MsCmd();
		c.setCmd(t);
		msPush(c);
	}
	
	public static void m(){
		msPush(MsCmd.CMD_MASTER);
	}
	
	public static void mm(){
		msPush(MsCmd.CMD_ALL_MASTER);
	}
	public static int getC(){
		MsCmd c=msget();
		if(c==null){
			return MsCmd.CMD_NULL;
		}
		if(c.getCmd()==MsCmd.CMD_MASTER){
			msUnload();
		}
		return c.getCmd();
	}
	
	public static boolean c(){
		int _c=getC();
		if(_c==MsCmd.CMD_MASTER||_c==MsCmd.CMD_ALL_MASTER){
			return true;
		}
		return false;
	}
	
}
