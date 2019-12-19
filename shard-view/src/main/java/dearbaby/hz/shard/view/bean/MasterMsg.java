package dearbaby.hz.shard.view.bean;

import dearbaby.hz.shard.view.db.DbRecord;

public class MasterMsg {
 
	public static final int type_sel=10;

	public static final int type_reset=20;
	
	private Long time;
	
	private int type;

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	

}
