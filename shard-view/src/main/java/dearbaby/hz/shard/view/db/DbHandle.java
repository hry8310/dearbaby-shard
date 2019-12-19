package dearbaby.hz.shard.view.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

public class DbHandle {

	DbSource dbSource;
	
	private Connection getCon()throws Exception{
		Connection conn=dbSource.getConnection();
		return conn;
	}
	public ResultSet select(String sql)throws Exception{
		
		PreparedStatement ps = getCon().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public int update(String sql){
		try{
		    PreparedStatement ps = getCon().prepareStatement(sql);
            return ps.executeUpdate();
		}catch(Exception e){
			return -1;
		}
	}
	
	public DbRecord selRecord() throws Exception{
		ResultSet rs=select(DbRecord.retSel());
		DbRecord rec=new DbRecord();
		while(rs.next()){
			Long id = rs.getLong("id");

			Long time = rs.getLong("time");
			rec.setTime(time);
			rec.setId(id);
		}
		return rec;
	} 
	
	public int updRecord(Long time){
		return update(DbRecord.retUpd(time));
	}
	public DbSource getDbSource() {
		return dbSource;
	}
	public void setDbSource(DbSource dbSource) {
		this.dbSource = dbSource;
	}
	
	
}
