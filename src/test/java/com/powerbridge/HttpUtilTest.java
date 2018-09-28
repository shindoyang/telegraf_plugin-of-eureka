package com.powerbridge;

import com.powerbridge.util.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shindoyang on 2018/9/27.
 */
public class HttpUtilTest {
    public static void main(String[] args) {
        List<Map<String, String>> clientList = new ArrayList<>();

        //demo:代理访问
        String url = "http://172.16.0.43:8872/eureka/apps";
//        String url = "http://localhost:8761/eureka/apps";
        String para = "";

        String sr= HttpUtil.sendGet(url,para);
        JSONObject jsonObject = JSONObject.fromObject(sr);
        JSONObject applications = (JSONObject)jsonObject.get("applications");
        JSONArray application = (JSONArray)applications.get("application");
        for(int i = 0;i < application.size(); i++){
            JSONObject  a1 = (JSONObject)application.get(i);
            JSONArray instance = (JSONArray)a1.get("instance");
            for(int k= 0; k< instance.size(); k++){
                HashMap map = new HashMap();
                JSONObject k1 = (JSONObject)instance.get(k);
                String projectName = (String)k1.get("app");
                String ipAddr = (String)k1.get("ipAddr");
                JSONObject k2 = (JSONObject)k1.get("port");
                String projectPort = (Integer)k2.get("$") + "";
                map.put("projectName", projectName);
                map.put("projectIp", ipAddr);
                map.put("projectPort", projectPort);

                clientList.add(map);
            }
        }

        for(Map<String, String> temp : clientList){
            for(Map.Entry entry : temp.entrySet()){
                System.out.println(entry.getKey() + "," + entry.getValue());
                System.out.println();
            }
        }
    }

}
