package com.powerbridge.service;

import java.util.List;

/**
 * @Description:    telegraf服务类
 * @Author:         shindo
 * @CreateDate:     2018/9/25 11:37
 * @UpdateUser:     shindo
 * @UpdateDate:     2018/9/25 11:37
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public interface ITelegrafService {

    /**
     * 重置telegraf 配置文件
     * @author      shindo
     * @param       []
     * @return      void
     * @date        2018/9/25 11:38
     * @exception
     */
    void reNewTelegrafConf(List<String> projectInfos) throws Exception;
}
