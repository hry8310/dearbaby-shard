package dearbaby.hz.shard.view.net.handle;

import dearbaby.hz.shard.view.bean.MasterHandleParam;
import dearbaby.hz.shard.view.bean.MasterHandleReco;
import dearbaby.hz.shard.view.bean.SlaveHandleParam;
import dearbaby.hz.shard.view.bean.SlaveHandleReco;
import dearbaby.hz.shard.view.db.DbHandle;
import dearbaby.hz.shard.view.db.DbRecord;
import dearbaby.hz.shard.view.task.TaskManager;

public   class DefTaskHandle extends BaseTaskHandle {
 
	
	
	public   MasterHandleReco  masterHandle(MasterHandleParam param)  {
		handle.updRecord(param.getCurrentTime());
		MasterHandleReco tet=new MasterHandleReco();
		
		return tet;
	}
	public   SlaveHandleReco  slaveHandle(SlaveHandleParam param) {
		SlaveHandleReco ret=new SlaveHandleReco();
		try{
			DbRecord dbReco=handle.selRecord();
	
			ret.setDbReco(dbReco);
			ret.setTime(dbReco.getTime());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ret;
	}
 
	
	
}
