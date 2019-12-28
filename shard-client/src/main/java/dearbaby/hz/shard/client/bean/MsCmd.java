package dearbaby.hz.shard.client.bean;

public class MsCmd {


	public static final int  CMD_NULL=-1;
	
	public static final int  CMD_MASTER=10;

	public static final int  CMD_ALL_MASTER=20;
	
	private int cmd;

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}
	
	
}
