package dearbaby.hz.shard.view.bean;

import dearbaby.hz.shard.view.db.DbRecord;

public class SlaveMsg {

	private Integer threadNum;
	
	private Long recoTime;

	private Long masterTime;
	
	
	private DbRecord record;

	 
	
	

	public Integer getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(Integer threadNum) {
		this.threadNum = threadNum;
	}

	 

	public Long getRecoTime() {
		return recoTime;
	}

	public void setRecoTime(Long recoTime) {
		this.recoTime = recoTime;
	}

	public Long getMasterTime() {
		return masterTime;
	}

	public void setMasterTime(Long masterTime) {
		this.masterTime = masterTime;
	}

	public DbRecord getRecord() {
		return record;
	}

	public void setRecord(DbRecord record) {
		this.record = record;
	}
	
	

}
