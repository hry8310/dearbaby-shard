package dearbaby.hz.shard.client.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dearbaby.hz.shard.client.common.Utils;

public class Viewer {
	
	public static final int netStatusOk=20;

	public static final int netStatusErr=10;
	private static volatile int netStatus=10;
	
	private static volatile int status;
	
	private static volatile int level;
	
	private static volatile Date updTime;
	
	private static volatile List<String> okSlaves; 

	public static void setStatus(SlaveStatus _status){
		status=_status.getStatus();
		level=_status.getLevel();
		updTime=new Date();
		okSlaves=_status.getOkList();
	}

	public static List<String> getSlaves() {
		if(netStatus==netStatusOk){
			return okSlaves;
		}
		return new ArrayList<String>();
	}
	
	public static int getNetStatus() {
		return netStatus;
	}


	public static void setNetStatus(int netStatus) {
		Viewer.netStatus = netStatus;
	}


	public static int getStatus() {
		return status;
	}

	public static void setStatus(int status) {
		Viewer.status = status;
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		Viewer.level = level;
	}

	public static Date getUpdTime() {
		return updTime;
	}

	public static void setUpdTime(Date updTime) {
		Viewer.updTime = updTime;
	}

	public static List<String> getOkSlaves() {
		return okSlaves;
	}

	public static void setOkSlaves(List<String> okSlaves) {
		Viewer.okSlaves = okSlaves;
	}

 
 
	
	
	  
}
