package dearbaby.hz.shard.view.net.handle;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import dearbaby.hz.shard.view.bean.MonitorStatus;
import dearbaby.hz.shard.view.common.Utils;

public class MonitorHandler implements HttpHandler{
	@Override
    public void handle(HttpExchange exchange) {
        String response = JSON.toJSONString(MonitorStatus.slaveStatus);
         
        try{
             
            String queryString =  exchange.getRequestURI().getQuery();
            Map<String,String> queryStringInfo = Utils.formData2Dic(queryString);
             
            String postString = IOUtils.toString(exchange.getRequestBody());
            Map<String,String> postInfo = Utils.formData2Dic(postString);

            exchange.sendResponseHeaders(200,0);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }catch (IOException ie) {

        } catch (Exception e) {

        }
    }
}
