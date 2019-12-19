package dearbaby.hz.shard.view.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DataSourceConfig {

	private static DbSource master;
	private static List<DbSource> slaves;
	public static Properties properties;
	
	public static void buildDataSource(Properties p){
		master=new DbSource();
		
		String driverClassName = p.getProperty("master.driverClassName");  
        String url = p.getProperty("master.url");  
        String user = p.getProperty("master.username");  
        String psw = p.getProperty("master.password");  
        master.setDataSource(getDataSource(url,user,psw,driverClassName));
        
        String slaveList = p.getProperty("slave.list");
        String[] slaveNames=slaveList.split(",");
        slaves= new  ArrayList<DbSource>();
        for(String n :slaveNames ){
        	n=n.trim();
        	
        	String _driverClassName = p.getProperty(n+".driverClassName");  
            String _url = p.getProperty(n+".url");  
            String _user = p.getProperty(n+".username");  
            String _psw = p.getProperty(n+".password");  
            DbSource slave=new DbSource();
            slave.setName( n);
            slave.setDataSource(getDataSource(_url,_user,_psw,_driverClassName));
            slaves.add(slave);
        }
		
	}
	
	public static DataSource getDataSource(String url,String user,String psw,String drvClass){
		
		    BasicDataSource ds = new BasicDataSource();  
	        ds.setUrl(url);  
	        ds.setDriverClassName(drvClass);  
	        ds.setUsername(user);  
	        ds.setPassword(psw);  
	        return ds;
		
	}

	public static DbSource getMaster() {
		return master;
	}

	public static void setMaster(DbSource master) {
		DataSourceConfig.master = master;
	}

	 
	public static List<DbSource> getSlaves() {
		return slaves;
	}

	public static void setSlaves(List<DbSource> slaves) {
		DataSourceConfig.slaves = slaves;
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		DataSourceConfig.properties = properties;
	}
	
	
}
