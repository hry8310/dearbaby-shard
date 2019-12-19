package dearbaby.hz.shard.client.common;

import java.util.ArrayList;
import java.util.List;

import dearbaby.hz.shard.client.bean.NetMsg;
import dearbaby.hz.shard.client.bean.NetRspPack;
import dearbaby.hz.shard.client.bean.SlaveStatus;

public class Utils {

	public static String parseNetRspPackContent(NetRspPack pack){
		if(pack.getContent()==null||pack.getContent().length()==0){
			return pack.getContent();
		}
		//clean last $
		return pack.getContent().substring(0, pack.getContent().length()-1);
	}
	
	public static NetMsg parseNetRspPack(NetRspPack pack){
		 
		String c=parseNetRspPackContent(pack);
		return parseNetRspPack(c);
	}
	
	public static NetMsg parseNetRspPack(String c){
		NetMsg msg=new NetMsg();
	 
		String[] cs=c.split("\\|");
		if(cs.length<2){
			return msg;
		}

		msg.setOkSlave(Integer.valueOf(cs[0]));
		msg.setAllSlave(Integer.valueOf(cs[1]));
		if(cs.length>2){
			msg.setOks(cs[2]);
		}
		return msg;
	}
	
	public static SlaveStatus analysisNetMsg(NetMsg msg){
		SlaveStatus status=new SlaveStatus();
		if(msg.getAllSlave()==0||msg.getOkSlave()==0){
			status.setStatus(status.STATUS_ERR);
		}else if(msg.getAllSlave()>msg.getOkSlave()){
			status.setStatus(status.STATUS_WARN);
			status.setLevel((msg.getOkSlave()*10)/msg.getAllSlave());
		}else{
			status.setStatus(status.STATUS_OK);
		}
		status.setOks(msg.getOks());
		return status;
		
	}
	
	public static SlaveStatus analysisPack(NetRspPack pack){
		 
		NetMsg msg=parseNetRspPack(pack);
		return analysisNetMsg(msg);
	}
	
	public static List<String> str2List(String str){
		List<String> strs=new ArrayList<String>();
		String[] ss=str.split(",");
		for(String s:ss){
			strs.add(s);
		}
		
		return strs;
	}
}
