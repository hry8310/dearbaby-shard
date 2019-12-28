package dearbaby.hz.shard.view;

import dearbaby.hz.shard.view.net.NetManager;
import dearbaby.hz.shard.view.task.TaskManager;

public class ViewApp {
	private  static TaskManager taskMamager;
	

	private  static NetManager netMamager;


	public static TaskManager getTaskMamager() {
		return taskMamager;
	}


	protected static void setTaskMamager(TaskManager taskMamager) {
		ViewApp.taskMamager = taskMamager;
	}


	public static NetManager getNetMamager() {
		return netMamager;
	}


	protected static void setNetMamager(NetManager netMamager) {
		ViewApp.netMamager = netMamager;
	}
	
	
	

}
