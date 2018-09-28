package com.powerbridge.service.impl;

import com.powerbridge.config.SupportConf;
import com.powerbridge.service.ITelegrafService;
import com.powerbridge.util.ExecLinuxCMD;
import com.powerbridge.util.FileUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shindoyang on 2018/9/25.
 */
@Service
public class TelegrafServiceImpl implements ITelegrafService {

    @Autowired
    private SupportConf conf;

    @Override
    public void reNewTelegrafConf(List<String> projectInfos)throws Exception{
        FileUtil fileUtil = new FileUtil(conf);
        ExecLinuxCMD cmd = new ExecLinuxCMD(conf);

        //以telegraf.conf.bak为基准，拷贝一份telegraf.temp文件
        cmd.createTempFile();

        //修改telegraf.temp文件 ，注入所有注册到Eureka的服务
        fileUtil.reWriteConf(projectInfos);

        //将telegraf.temp重命名为telegraf.conf，覆盖正在使用的配置文件
        cmd.reNewConfFile();

        //重启telegraf
        cmd.restartTelegraf();

    }
}
