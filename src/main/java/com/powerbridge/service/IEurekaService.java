package com.powerbridge.service;

import java.util.List;

/**
 * @Description:    Eureka注册中心的api接口封装
 * @Author:         shindo
 * @CreateDate:     2018/9/25 10:13
 * @UpdateUser:     shindo
 * @UpdateDate:     2018/9/25 10:13
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
public interface IEurekaService {

    /**
     * 方法实现说明
     * @author      shindo
     * @param       [type] 0：启动时获取的所有注册服务，1：跑批时获取的所有注册服务
     * @return      java.util.List<java.lang.String>
     * @date        2018/9/25 10:34
     * @exception   
     */
    List<String> getRegisterProjects(int type);

    /**
     * 注册中心服务有边，重置系统静态变量
     * @author      shindo
     * @param       []
     * @return      void
     * @date        2018/9/25 14:42
     * @exception
     */
    void reSetRegisterList(List<String> newRegisterList);
}
