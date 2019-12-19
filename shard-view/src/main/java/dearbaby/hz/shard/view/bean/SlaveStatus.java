package dearbaby.hz.shard.view.bean;

public class SlaveStatus {

	private Long threadNum;
	
	private double timeout=0;
	
	private double delay=0;
	
	private double delayTime=0;
	
	private double lastOk=0;
	
	private double lastErr=0;
	

	private String name;
	
	public void reset(){
		timeout=0;
		delay=0;
		delayTime=0;
		lastOk=0;
		lastErr=0;
	}
	
	public void addTimeout(){
		timeout++;
		lastErr++;
		lastOk=0;
	}
	
	 
	
	public void addDelay(int time){
		delay++;

		lastErr++;
		delayTime=delayTime+time;
		lastOk=0;
	}
	
	public void ok(){
		
		lastOk++;
		lastErr=0;
	}

	
	public Long getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(Long threadNum) {
		this.threadNum = threadNum;
	}

	public double getTimeout() {
		return timeout;
	}

	public void setTimeout(double timeout) {
		this.timeout = timeout;
	}

	public double getDelay() {
		return delay;
	}

	public void setDelay(double delay) {
		this.delay = delay;
	}

	public double getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(double delayTime) {
		this.delayTime = delayTime;
	}

	public double getLastOk() {
		return lastOk;
	}

	public void setLastOk(double lastOk) {
		this.lastOk = lastOk;
	}

	public double getLastErr() {
		return lastErr;
	}

	public void setLastErr(double lastErr) {
		this.lastErr = lastErr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
