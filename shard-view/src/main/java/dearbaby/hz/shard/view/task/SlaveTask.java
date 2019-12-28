package dearbaby.hz.shard.view.task;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

import dearbaby.hz.shard.view.bean.MasterMsg;
import dearbaby.hz.shard.view.bean.SlaveHandleParam;
import dearbaby.hz.shard.view.bean.SlaveHandleReco;
import dearbaby.hz.shard.view.bean.SlaveMsg;
import dearbaby.hz.shard.view.bean.SlaveStatus;
import dearbaby.hz.shard.view.db.DbHandle;
import dearbaby.hz.shard.view.db.DbRecord;

public class SlaveTask extends DbTask {
	

	protected Integer threadNum;
	
	private volatile  boolean   isClean=true;

	private LinkedBlockingQueue<MasterMsg> masterMsgList;
	
	CountDownLatch countDown;
	
    private SlaveStatus status;
	
	public SlaveTask(){
		masterMsgList=new LinkedBlockingQueue<MasterMsg> ();
	}

	public  void exe() {
		try{
			MasterMsg masterMsg=getMasterMsg();
			if(masterMsg==null){
				return ;
			}
			//System.out.println("xxxxxxcccc "+masterMsg.getTime());
			if(masterMsg.getType()==MasterMsg.type_sel	){
				//DbRecord dr=handle.selRecord();
				SlaveHandleParam param=new SlaveHandleParam();
				SlaveHandleReco reco=taskHandle.slaveHandle(param);
				SlaveMsg smsg=new  SlaveMsg();
				smsg.setRecord(reco.getDbReco());
				smsg.setTime(reco.getDbReco().getTime());
				smsg.setThreadNum(threadNum);
				putSlaveMsg(smsg);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private MasterMsg  getMasterMsg() throws Exception{
		MasterMsg m= masterMsgList.take();
		MasterMsg m2=null;
		while(true){
			m2 =masterMsgList.poll();
			if(m2==null){
				break;
			}else{
				m=m2;
			}
		}
		isClean=false;
		return m;
	} 
	
	private void putSlaveMsg(SlaveMsg msg) throws Exception{
		if(isClean==true){
			slaveMsgList.clear();
		} 
		slaveMsgList.add(msg);
		if(count!=null){
			count.countDown();
		}
	}
	
	public void putMasterMsg(MasterMsg msg) {
		 
		masterMsgList.add(msg);
	}
	
    public void clean(){
    	isClean=true;
    	slaveMsgList.clear();
    }

 
	public void buildCountDown(){
		countDown=new CountDownLatch(tm.getSlaveTasks().size());
		
	}

 

	public Integer getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(Integer threadNum) {
		this.threadNum = threadNum;
	}

	public LinkedBlockingQueue<MasterMsg> getMasterMsgList() {
		return masterMsgList;
	}

	public void setMasterMsgList(LinkedBlockingQueue<MasterMsg> masterMsgList) {
		this.masterMsgList = masterMsgList;
	}

	public CountDownLatch getCount() {
		return count;
	}

	public synchronized void  setCount(CountDownLatch count) {
		this.count = count;
	}

	public SlaveStatus getStatus() {
		return status;
	}

	public void setStatus(SlaveStatus status) {
		this.status = status;
	}
	
	
	
	
	
}