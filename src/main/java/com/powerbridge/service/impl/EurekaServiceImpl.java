package com.powerbridge.service.impl;

import com.powerbridge.config.SupportConf;
import com.powerbridge.config.SystemConf;
import com.powerbridge.service.IEurekaService;
import com.powerbridge.util.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shindoyang on 2018/9/25.
 */
@Service
public class EurekaServiceImpl implements IEurekaService {

    private Log log = LogFactory.getLog(EurekaServiceImpl.class);

    @Autowired
    private SupportConf conf;

    @Override
    public List<String> getRegisterProjects(int type) {
        List<String> cornEurekaList = new ArrayList<>();

        //获取eureka中注册的所有服务
        String eurekaProjects= HttpUtil.sendGet(conf.getEurekaApi(), "");

        //解析获取服务的name/host/port信息
        JSONObject jsonObject = JSONObject.fromObject(eurekaProjects);
        log.debug("eureka接口响应信息：" + jsonObject.toString());
        JSONObject applications = (JSONObject)jsonObject.get("applications");
        JSONArray application = (JSONArray)applications.get("application");//项目节点数组
        for(int i = 0;i < application.size(); i++){
            JSONObject  a1 = (JSONObject)application.get(i);
            JSONArray instance = (JSONArray)a1.get("instance");
            for(int k= 0; k< instance.size(); k++){
                JSONObject k1 = (JSONObject)instance.get(k);
                String projectName = (String)k1.get("app");
                String ipAddr = (String)k1.get("ipAddr");
                JSONObject k2 = (JSONObject)k1.get("port");
                String projectPort = k2.get("$") + "";
                StringBuffer sb = new StringBuffer();
                String projectInfo = sb.append(projectName).append(":").append(ipAddr).append(":").append(projectPort).toString();
                if(type == 0)
                    SystemConf.eurekaList.add(projectInfo);
                if(type == 1)
                    cornEurekaList.add(projectInfo);
            }
        }

        List<String> tempList = type == 0 ? SystemConf.eurekaList : cornEurekaList;

        //下面for循环的打印信息是开发跟踪，后期可屏蔽掉
        for(String projectInfo : tempList){
            log.info(projectInfo);
        }

        if(type ==0) {
            log.info("系统启动时，注册中心服务数量：" + tempList.size());
        }

        return tempList;
    }

    @Override
    public void reSetRegisterList(List<String> newRegisterList) {
        SystemConf.eurekaList.clear();
        log.info("清空系统原记录列表，当前列表长度：" + SystemConf.eurekaList.size());
        SystemConf.eurekaList.addAll(newRegisterList);
        log.info("加入注册中心最新的服务列表");
    }
}
