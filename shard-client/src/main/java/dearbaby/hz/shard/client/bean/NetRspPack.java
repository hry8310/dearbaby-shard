package dearbaby.hz.shard.client.bean;

public class NetRspPack {

	public static final int STATUS_ERR=10;

	public static final int STATUS_OK=0;

	private int status;
	
	private String content;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
