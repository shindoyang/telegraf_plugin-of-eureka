package com.powerbridge.scheduled;

import com.powerbridge.config.SystemConf;
import com.powerbridge.service.IEurekaService;
import com.powerbridge.service.ITelegrafService;
import com.powerbridge.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shindoyang on 2018/9/20.
 */
@Service
public class ScheduledServer {

    private Log log = LogFactory.getLog(ScheduledServer.class);

    @Autowired
    private IEurekaService eurekaService;

    @Autowired
    private ITelegrafService telegrafService;

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void testSchedule(){
        try {
            boolean reNew = false;
            log.info("当前跑批时间：" + DateUtil.get_yyyy_MM_dd_HH_mm_ss());
            List<String> cornEurekaList = eurekaService.getRegisterProjects(1);

            log.info("当前注册中心服务数量：" + cornEurekaList.size());
            if(cornEurekaList.size() > SystemConf.eurekaList.size()){
                log.info("当前注册服务数量多于系统记录的服务数，重置telegraf.conf");
                //重置系统静态list
                eurekaService.reSetRegisterList(cornEurekaList);
                //重置telegraf
                telegrafService.reNewTelegrafConf(cornEurekaList);

            }else{
                for(String currentRegisterProject : cornEurekaList){
                    boolean hasExist = false;
                    for(String registerPro : SystemConf.eurekaList){
                        if(currentRegisterProject.equals(registerPro)){
                            hasExist = true;
                            break;
                        }
                    }
                    if(!hasExist){
                        reNew = true;
                    }
                }
                if(reNew){
                    log.info("发现新注册的服务，重置telegraf.conf");
                    //重置系统静态list
                    eurekaService.reSetRegisterList(cornEurekaList);
                    //重置telegraf
                    telegrafService.reNewTelegrafConf(cornEurekaList);
                }else {
                    log.info("没有发现注册列表变动");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
