package dearbaby.hz.shard.view.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import dearbaby.hz.shard.view.bean.MasterHandleParam;
import dearbaby.hz.shard.view.bean.MasterMsg;
import dearbaby.hz.shard.view.bean.MonitorStatus;
import dearbaby.hz.shard.view.bean.NetMsg;
import dearbaby.hz.shard.view.bean.SlaveMsg;
import dearbaby.hz.shard.view.common.Utils;
import dearbaby.hz.shard.view.common.ViewConfig;
import dearbaby.hz.shard.view.db.DbHandle;
import dearbaby.hz.shard.view.db.DbRecord;

public class MasterTask extends DbTask {
	
	private long currentTime;

    private LinkedBlockingQueue<NetMsg> netMsgList;
	public  void exe() {
		try{
			currentTime=TaskStatus.getAndAddTime();
			MasterHandleParam param=new MasterHandleParam();
			param.setCurrentTime(currentTime);
			taskHandle.masterHandle(param);
			sleep(200l);
			//handle.updRecord(currentTime);
			putMasterMsg(MasterMsg.type_sel);
		    count.await(1000, TimeUnit.MILLISECONDS);
		    List<SlaveMsg> msgs= getSlavesMsg(tm.getSlaveTasks().size());
		    NetMsg netMsg=analysisSlave(msgs);
		    netMsgList.add(netMsg);
		   // System.out.println("netMsg   "+netMsg.getOkSlaver());
		    sleep(ViewConfig.interval);
		    if(currentTime%3==0){
		    //	tm.getSlaverTasks().get((int)(currentTime%2)).reset();
		    }
		    
		}catch(Exception e){
			try{
				sleep(ViewConfig.interval/2);
			}catch(Exception ee){
				
			}
			e.printStackTrace();
		}finally{
			
		}
		
	}
	
	private void putMasterMsg(int type){
		MasterMsg mmsg=new MasterMsg();
		mmsg.setTime(currentTime);
		mmsg.setType(type);
		count=new CountDownLatch(tm.getSlaveTasks().size());
		
		for(SlaveTask st:tm.getSlaveTasks()){
			st.setCount(count);
		} 
		for(SlaveTask st:tm.getSlaveTasks()){
			st.putMasterMsg(mmsg);
		} 
	}
	
	private List<SlaveMsg> getSlavesMsg(int num){
		ArrayList<SlaveMsg> msgs=new ArrayList<SlaveMsg>();
		
		int rei=0;
		for(int i=0;i<num;i++){
			SlaveMsg m=slaveMsgList.poll();
			if(m!=null){
				msgs.add(m);
				rei++;
				
			}
		}
		
		
		
		if(rei<num){
			
			for(SlaveTask st:tm.getSlaveTasks()){
				Utils.resetSlave(st, msgs);
			}
		}
		return msgs;
		
	}
	
    private void proTimeout( ArrayList<Integer> thNums ){
    	for(SlaveTask st:tm.getSlaveTasks()){
			int num = st.getThreadNum();
			boolean find=false;
			for(Integer tn:thNums){
				if(tn.intValue()==num){
					find=true;
					break;
				}
			}
			if(find==false){
				MonitorStatus.addTimeout(num);
			}
		}
    	
    }	
	
	private NetMsg analysisSlave(List<SlaveMsg> msgs){

		ArrayList<Integer> thNums=new ArrayList<Integer>();

		ArrayList<Integer> okNum=new ArrayList<Integer>();
		
		int ret=0;
		for(SlaveMsg m:msgs){
			thNums.add(m.getThreadNum());
			if(m.getTime()>=currentTime){
				ret++;
				
				MonitorStatus.ok(m.getThreadNum());
				okNum.add(m.getThreadNum());
			}else{ 
				System.out.println("xxxx  "+m.getTime()+"  , need:  "+currentTime);
				long time=m.getTime()-currentTime;
				if(time>10000000000000l){
					time=1;
				}
				MonitorStatus.addDelay(m.getThreadNum().intValue(),time);
			}
		}
		proTimeout(thNums);
		
		NetMsg netMsg=new NetMsg();
		netMsg.setOkSlave(ret);
		netMsg.setAllSlave(tm.getSlaveTasks().size());
		netMsg.setOks(Utils.listInts2NameStr(okNum,tm.getSlaveTasks()));
		return netMsg;
		
		
	}

	public LinkedBlockingQueue<NetMsg> getNetMsgList() {
		return netMsgList;
	}

	public void setNetMsgList(LinkedBlockingQueue<NetMsg> netMsgList) {
		this.netMsgList = netMsgList;
	}
	
	
	
	
}