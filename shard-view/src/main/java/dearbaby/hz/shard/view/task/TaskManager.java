package dearbaby.hz.shard.view.task;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

import dearbaby.hz.shard.view.bean.MonitorStatus;
import dearbaby.hz.shard.view.bean.NetMsg;
import dearbaby.hz.shard.view.bean.SlaveMsg;
import dearbaby.hz.shard.view.bean.SlaveStatus;
import dearbaby.hz.shard.view.common.Utils;
import dearbaby.hz.shard.view.common.ViewConfig;
import dearbaby.hz.shard.view.db.DataSourceConfig;
import dearbaby.hz.shard.view.db.DbHandle;
import dearbaby.hz.shard.view.db.DbSource;
import dearbaby.hz.shard.view.net.handle.BaseTaskHandle;

public class TaskManager {

	private ArrayList<SlaveTask> slaveTasks;
	
	private MasterTask masterTask;
	
	 
	
	private ConcurrentLinkedQueue<SlaveMsg> slaveMsgList;
	
	private LinkedBlockingQueue<NetMsg> netMsgList;
	
	public TaskManager(){
		
		netMsgList= new LinkedBlockingQueue<NetMsg>();
		slaveMsgList= new ConcurrentLinkedQueue<SlaveMsg>();
		
	}
	
	public void start(){
		buildMaster();
		buildSlave();
		
		for(SlaveTask st:slaveTasks){
			st.start();
		}
		masterTask.start();
	}
	public void buildSlave(){
		int num=0;
		slaveTasks=new ArrayList<SlaveTask>();
		for(DbSource db :DataSourceConfig.getSlaves()){
			//System.out.println("xxxxxxxxx_slaver");
			DbHandle hd=new DbHandle();
			
			hd.setDbSource(db);
			SlaveTask task=new SlaveTask( );
			task.setHandle(hd);
			task.setThreadNum(num++);
			task.setTm(this); 
			task.setSlaveMsgList(slaveMsgList);	
			task.setTaskHandle(Utils.handleInstall(ViewConfig.handleClass, this, hd, ViewConfig.handleParam));
			
		    slaveTasks.add(task);
		    
		    SlaveStatus ss=new SlaveStatus();
		    ss.setName(task.getHandle().getDbSource().getName());
		    task.setStatus(ss);
		    
		    MonitorStatus.slaveStatus.add(ss);
		    
		}
	}
	
	public void buildMaster(){
		masterTask=new MasterTask();
		DbHandle hd=new DbHandle();
		hd.setDbSource(DataSourceConfig.getMaster()); 
		masterTask.setHandle(hd); 
		masterTask.setTm(this);
		masterTask.setSlaveMsgList(slaveMsgList);	
		masterTask.setNetMsgList(netMsgList);
		masterTask.setTaskHandle(Utils.handleInstall(ViewConfig.handleClass, this, hd, ViewConfig.handleParam));
		
	}

	public ArrayList<SlaveTask> getSlaveTasks() {
		return slaveTasks;
	}

	public void setSlaveTasks(ArrayList<SlaveTask> slaveTasks) {
		this.slaveTasks = slaveTasks;
	}

	public MasterTask getMasterTask() {
		return masterTask;
	}

	public void setMasterTask(MasterTask masterTask) {
		this.masterTask = masterTask;
	}

	public ConcurrentLinkedQueue<SlaveMsg> getSlaveMsgList() {
		return slaveMsgList;
	}

	public void setSlaveMsgList(ConcurrentLinkedQueue<SlaveMsg> slaveMsgList) {
		this.slaveMsgList = slaveMsgList;
	}

	public LinkedBlockingQueue<NetMsg> getNetMsgList() {
		return netMsgList;
	}

	public void setNetMsgList(LinkedBlockingQueue<NetMsg> netMsgList) {
		this.netMsgList = netMsgList;
	}
	
	
	 
	
	
}
