package dearbaby.hz.shard.view.bean;

import dearbaby.hz.shard.view.db.DbRecord;

public class SlaveMsg {

	private Integer threadNum;
	
	private Long time;
	
	private DbRecord record;

	 
	
	

	public Integer getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(Integer threadNum) {
		this.threadNum = threadNum;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public DbRecord getRecord() {
		return record;
	}

	public void setRecord(DbRecord record) {
		this.record = record;
	}
	
	

}
