
   <strong>dearbaby-shard 是什么：</strong>
   <p>dearbaby dearbaby-shard 数据库相关的系列组件。主要用于mysql读写分离后，主从同步的及时性。
   <ul style='margin-left:60px;'>
   <li>工具主要包含两大组件:shard-view、shard-client。其中 shard-view 是监听服务，实时收集同步信息，并将信息传递给shard-client</li>
   <li>shard-view 是一个独立运行的服务，源码中包含了liunx的启动脚本start.sh 作为参考，可以根据实际调整 </li>
   <li>shard-client 是一个工具包。主要对外提供api，可以加入到应用里。并且纳入spring管理，配置文件参考：spring-cfg-demo.xml</li>
   <li>shard-shardingphere 是一个实际应用demo。居于shard-client 在 shardingphere的 MssterSlaveRoute的应用</li>
   </ul>
   </p>
   
   <p>
   <strong>它的原理是：</strong><br/> 
   在读写分离的环境下,shard-view 定期向主库更新一个监控信息（update 表 dearbaby_hz_shard_tbl_heat）。之后在从库读取监控信息是否最新。并且将分析信息传播给
   shard-client。居于shard-client的应用可以根据分析内容定于适合自己的决策
   </p>
   
    
   <strong>有哪些限制：</strong>
   <li>该工具不是解决同步的延迟问题，他只是监控同步的状态，并收集信息给应用</li>
   <li>该工具不针对特定的事务监控，所以对于长时间事务，读写的分离决策不能依赖工具的分析。</li>
   <li>该工具更适合某些时段，从库可能处于高负荷状态导致同步有轻微的延迟，应用能够感知并将流向避开</li>
   <br/><br/>
   
   <strong>持续更新中.....</strong>
