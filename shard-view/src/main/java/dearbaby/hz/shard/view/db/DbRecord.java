package dearbaby.hz.shard.view.db;

public class DbRecord {

	private String tableName="dearbaby_hz_shard_tbl_heat";
	
	private Long id;
	
	private Long time;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public static String retSel(){
		
		return "select id,time from dearbaby_hz_shard_tbl_heat ";
	}
	
	public static String retUpd(Long time,Long id){
		return "update dearbaby_hz_shard_tbl_heat set time="+time+" where id="+id;
	}
	
	public static  String retUpd(Long time){
		return retUpd(time ,1l);
	}
	
	
	
	
}
