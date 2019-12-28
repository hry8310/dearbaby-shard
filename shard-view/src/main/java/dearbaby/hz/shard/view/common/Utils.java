package dearbaby.hz.shard.view.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dearbaby.hz.shard.view.bean.NetMsg;
import dearbaby.hz.shard.view.bean.SlaveMsg;
import dearbaby.hz.shard.view.db.DbHandle;
import dearbaby.hz.shard.view.net.handle.BaseTaskHandle;
import dearbaby.hz.shard.view.task.SlaveTask;
import dearbaby.hz.shard.view.task.TaskManager;

public class Utils {

	public static boolean findSlaveMsg(long threadNum,List<SlaveMsg> msgs){
		boolean find=false;
		for(SlaveMsg m:msgs){
			if(m.equals(threadNum)){
				find=true;
				break;
			}
		}
		return find;
	}
	
	public static BaseTaskHandle handleInstall( String className,
			TaskManager tm,DbHandle handle,String param){
		try {
			if(className==null||className.length()==0){
				className="dearbaby.hz.shard.view.net.handle.DefTaskHandle";
			}
            Class clz = Class.forName(className);
            BaseTaskHandle obj = (BaseTaskHandle)clz.newInstance();
            obj.setHandle(handle);
            obj.setTm(tm);
            if(param!=null){
            	  obj.extParam(param);
            }
          
            return obj;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return null;
	}
	
	
	public  static  void resetSlave(SlaveTask st,List<SlaveMsg> msgs){
		 long thn=st.getThreadNum();
		 if(findSlaveMsg(thn,msgs)==false){
			 st.clean();
		 }
	}
	
	public static String netMsg2Str(NetMsg msg){
		return msg.getAllSlave()+"|"+msg.getOkSlave()+"|"+msg.getOks();
	}
	
	public static String listInts2Str(List<Integer> list){
		String str="";
		int len=list.size();
		for(int i=0;i<len;i++){
			str=str+list.get(i).intValue();
			if(i<len-1){
				str=str+",";
			}
		}
		
		return str;
	}
	
	public static String listInts2NameStr(List<Integer> list,List<SlaveTask> tasks){
		String str="";
		int len=list.size();
		for(int i=0;i<len;i++){
			int idx=list.get(i).intValue();
			
			str=str+tasks.get(idx).getHandle().getDbSource().getName();
			if(i<len-1){
				str=str+",";
			}
		}
		
		return str;
	}
	
	public static Map<String,String> formData2Dic(String formData ) {
        Map<String,String> result = new HashMap<String,String>();
        if(formData== null || formData.trim().length() == 0) {
            return result;
        }
        final String[] items = formData.split("&");
        for(String item: items){ 	
            final String[] keyAndVal = item.split("=");
            if( keyAndVal.length == 2) {
                try{
                    final String key = URLDecoder.decode( keyAndVal[0],"utf8");
                    final String val = URLDecoder.decode( keyAndVal[1],"utf8");
                    result.put(key,val);
                }catch (UnsupportedEncodingException e) {}
            }
        };
        return result;
    }
}
