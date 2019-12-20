package org.apache.shardingsphere.core.route.router.masterslave;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 

import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.core.parse.SQLParseEngine;
import org.apache.shardingsphere.core.parse.sql.statement.SQLStatement;
import org.apache.shardingsphere.core.parse.sql.statement.dml.SelectStatement;
import org.apache.shardingsphere.core.route.SQLLogger;
import org.apache.shardingsphere.core.rule.MasterSlaveRule;

import dearbaby.hz.shard.client.bean.SlaveStatus;
import dearbaby.hz.shard.client.bean.Viewer;
import dearbaby.hz.shard.client.common.MsHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
  
/**
 * Master slave router interface.
 * 
 * @author zhangliang
 * @author panjuan
 */
@RequiredArgsConstructor
public  class DearBabyMasterSlaveRouter {
    
    private final MasterSlaveRule masterSlaveRule;
    
    private final SQLParseEngine parseEngine;
    
    private final boolean showSQL;
    
    /**
     * Route Master slave.
     *
     * @param sql SQL
     * @param useCache use cache or not
     * @return data source names
     */
    // TODO for multiple masters may return more than one data source
    public Collection<String> route(final String sql, final boolean useCache) {
    	
        if(Viewer.getStatus()==SlaveStatus.STATUS_ERR||(
        		Viewer.getStatus()==SlaveStatus.STATUS_WARN&&Viewer.getLevel()<8)
        		||MsHelper.c()){
        	return Collections.singletonList(masterSlaveRule.getMasterDataSourceName());
        }
    	Collection<String> result = route(parseEngine.parse(sql, useCache));
        if (showSQL) {
            SQLLogger.logSQL(sql, result);
        }
        System.out.println("XXXXCCCCC:    "+result );
        return result;
    }
    
    private Collection<String> route(final SQLStatement sqlStatement) {
        if (isMasterRoute(sqlStatement)) {
            MasterVisitedManager.setMasterVisited();
            return Collections.singletonList(masterSlaveRule.getMasterDataSourceName());
        }
        /*
        return Collections.singletonList(masterSlaveRule.getLoadBalanceAlgorithm().getDataSource(
                masterSlaveRule.getName(), masterSlaveRule.getMasterDataSourceName(), new ArrayList<>(masterSlaveRule.getSlaveDataSourceNames())));
        */
        Collection<String> slavesName=Viewer.getSlaves();
        if(slavesName.size()==0){
        	slavesName=masterSlaveRule.getSlaveDataSourceNames();
        }
        return Collections.singletonList(masterSlaveRule.getLoadBalanceAlgorithm().getDataSource(
                masterSlaveRule.getName(), masterSlaveRule.getMasterDataSourceName(), new ArrayList<>(slavesName)));
     
    }
    
    private boolean isMasterRoute(final SQLStatement sqlStatement) {
        return !(sqlStatement instanceof SelectStatement) || MasterVisitedManager.isMasterVisited() || HintManager.isMasterRouteOnly();
    }
}
