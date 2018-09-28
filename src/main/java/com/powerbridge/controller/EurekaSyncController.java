package com.powerbridge.controller;

import com.powerbridge.service.IEurekaService;
import com.powerbridge.service.ITelegrafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Description:    java类作用描述
 * @Author:         shindo
 * @CreateDate:     2018/9/21 10:07
 * @UpdateUser:     shindo
 * @UpdateDate:     2018/9/21 10:07
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Controller
public class EurekaSyncController {

    @Autowired
    private IEurekaService eurekaService;

    @Autowired
    private ITelegrafService telegrafService;

    /**
     * 将eureka中的服务同步到telegraf的conf文件中。
     * 每次系统启动，默认以telegraf.conf.bak备份文件为基础，重置eureka中的所有服务到telegraf.conf
     * @author      shindo
     * @param       []
     * @return      void
     * @date        2018/9/21 10:09
     * @exception   
     */
    @PostConstruct
    public void syncEurekaProject(){
        try {
            //获取eureka中注册的所有服务
            List<String> projectsList = eurekaService.getRegisterProjects(0);

            //重置telegraf.conf配置文件 --测试时屏蔽
            telegrafService.reNewTelegrafConf(projectsList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
