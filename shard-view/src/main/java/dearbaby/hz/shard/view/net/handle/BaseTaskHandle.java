package dearbaby.hz.shard.view.net.handle;

import dearbaby.hz.shard.view.bean.MasterHandleParam;
import dearbaby.hz.shard.view.bean.MasterHandleReco;
import dearbaby.hz.shard.view.bean.SlaveHandleParam;
import dearbaby.hz.shard.view.bean.SlaveHandleReco;
import dearbaby.hz.shard.view.db.DbHandle;
import dearbaby.hz.shard.view.task.TaskManager;

public abstract class BaseTaskHandle {

	protected TaskManager tm;
	protected DbHandle handle; 
	
	
	
	public abstract MasterHandleReco  masterHandle(MasterHandleParam param);
	public abstract SlaveHandleReco  slaveHandle(SlaveHandleParam param);

	public  void  extParam(String param){
		
	}
	
	
	
	public TaskManager getTm() {
		return tm;
	}
	public void setTm(TaskManager tm) {
		this.tm = tm;
	}
	public DbHandle getHandle() {
		return handle;
	}
	public void setHandle(DbHandle handle) {
		this.handle = handle;
	}
	
	
	
}
