
package org.apache.shardingsphere.core.route.router.masterslave;
 
import org.apache.shardingsphere.core.parse.SQLParseEngine; 
import org.apache.shardingsphere.core.rule.MasterSlaveRule;
  
 
 
public final class MasterSlaveRouter extends DearBabyMasterSlaveRouter {
    
     public MasterSlaveRouter(MasterSlaveRule r,SQLParseEngine s,boolean showSQL){
    	 super(r,s,showSQL);
     }
}
