package dearbaby.hz.shard.view.task;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

import dearbaby.hz.shard.view.bean.SlaveMsg;
import dearbaby.hz.shard.view.db.DbHandle;
import dearbaby.hz.shard.view.net.handle.BaseTaskHandle;

public 	  class DbTask extends Thread {

	protected DbHandle handle;
	
	protected boolean isStop=false;

	protected ConcurrentLinkedQueue<SlaveMsg> slaveMsgList;
	
	
	protected CountDownLatch count;
	
	
	protected TaskManager tm;
	

	protected BaseTaskHandle taskHandle;
	
	
	public DbTask(){
		
	} 
	public DbTask(DbHandle h){
		handle=h;
	}
	
	public void shutdown(){
		isStop=true;
	}
	
	public void shutdownNow(){
		isStop=true;
		interrupt();
	}
	
	public void reset(){
		interrupt();
	}
	
	public void resetConn(){
		try{
			handle.getDbSource().reConnection();
		}catch(Exception e){
			
		}
		
		interrupt();
	}
	
	

	public   void exe(){
		System.out.println("nothing do");
	};
	public void run(){
		while(true){
			
			try{
				exe();		
			    if(isStop==true){
			    	break;
			    }	 
			}catch(Exception e){
				e.getStackTrace();
			}
			
		}	
	
	}
	
	public DbHandle getHandle() {
		return handle;
	}

	public void setHandle(DbHandle handle) {
		this.handle = handle;
	}
	
	
	public TaskManager getTm() {
		return tm;
	}
	public void setTm(TaskManager tm) {
		this.tm = tm;
	}
	 
	public ConcurrentLinkedQueue<SlaveMsg> getSlaveMsgList() {
		return slaveMsgList;
	}
	public void setSlaveMsgList(ConcurrentLinkedQueue<SlaveMsg> slaveMsgList) {
		this.slaveMsgList = slaveMsgList;
	}
	public CountDownLatch getCount() {
		return count;
	}
	public void setCount(CountDownLatch count) {
		this.count = count;
	}
	public BaseTaskHandle getTaskHandle() {
		return taskHandle;
	}
	public void setTaskHandle(BaseTaskHandle taskHandle) {
		this.taskHandle = taskHandle;
	}
	 
    
 
	
}
