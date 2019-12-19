package dearbaby.hz.shard.view.task;

import java.util.concurrent.atomic.AtomicLong;

public class TaskStatus {

	private static  AtomicLong time = new AtomicLong(0);
	
	public static void cleanTime(){
		time.set(0);
	}
	
	public static long getTime(){
		return time.get();
	}

	public static Long getAndAddTime(){
		return time.getAndAdd(1);
	}
	
	public static Long addAndGetTime(){
		return time.addAndGet(1);
	}
}
