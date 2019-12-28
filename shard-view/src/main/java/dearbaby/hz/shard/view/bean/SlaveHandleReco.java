package dearbaby.hz.shard.view.bean;

import dearbaby.hz.shard.view.db.DbRecord;

public class SlaveHandleReco {

	private int status;
	

	private long time;
	
	private DbRecord dbReco;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public DbRecord getDbReco() {
		return dbReco;
	}

	public void setDbReco(DbRecord dbReco) {
		this.dbReco = dbReco;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	
	
	
}
