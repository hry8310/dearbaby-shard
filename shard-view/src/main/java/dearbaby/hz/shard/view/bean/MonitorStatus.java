package dearbaby.hz.shard.view.bean;

import java.util.ArrayList;
import java.util.List;

public class MonitorStatus {

	public  static List<SlaveStatus> slaveStatus=new ArrayList<SlaveStatus>();
	
	public static void  reset(int idx){
		slaveStatus.get(idx).reset();
	}
	
	public static void ok(int idx){
		slaveStatus.get(idx).ok();
	}
	
	public static void addTimeout(int idx){
		slaveStatus.get(idx).addTimeout();
	}
	
	public static void addDelay(int idx,long time){
		slaveStatus.get(idx).addDelay((int)time);
	} 
}
