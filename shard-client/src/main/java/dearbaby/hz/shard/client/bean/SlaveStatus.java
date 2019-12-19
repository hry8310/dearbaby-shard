package dearbaby.hz.shard.client.bean;

import java.util.Date;
import java.util.List;

import dearbaby.hz.shard.client.common.Utils;

public class SlaveStatus {

	public static final  int STATUS_ERR=50;

	public static final  int STATUS_WARN=10;
	
	

	public static final int STATUS_OK=0;
	
	public   String oks; 
	
	public List<String> okList;
	private int status;
	
	private int level;
	
	private Date updTime;

	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Date getUpdTime() {
		return updTime;
	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public String getOks() {
		return oks;
	}

	public void setOks(String oks) {
		this.oks = oks;
		if(oks!=null&&oks.length()>0){
			setOkList(Utils.str2List(oks));
		}
	}

	public List<String> getOkList() {
		return okList;
	}

	public void setOkList(List<String> okList) {
		this.okList = okList;
	}
	
	
	
}
