package dearbaby.hz.shard.view.db;

import java.sql.Connection;

import javax.sql.DataSource;

public class DbSource {

	private DataSource dataSource;
	private Connection con;
	private String name;
	private String user;
	private String psw;
	private String url;
	private String driverClass;
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDriverClass() {
		return driverClass;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	
	public Connection getConnection() throws Exception{
		if(con==null||con.isClosed()){
			con=dataSource.getConnection();
			
		}
		return con;
	}
	
	public Connection reConnection() throws Exception{
		try{
			con.isClosed();
		}catch(Exception e){
			
		}
		con=dataSource.getConnection();
		return con;
	}
}
