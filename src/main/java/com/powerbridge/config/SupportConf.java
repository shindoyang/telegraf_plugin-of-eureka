package com.powerbridge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description:    获取properties配置文件的属性
 * @Author:         shindo
 * @CreateDate:     2018/9/21 16:06
 * @UpdateUser:     shindo
 * @UpdateDate:     2018/9/21 16:06
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Component
public class SupportConf {

    @Value("${eureka.api}")
    private String eurekaApi;

    @Value("${telegraf.conf.tempFile}")
    private String confTempFile;

    @Value("${telegraf.conf.cmd.createTempFile}")
    private String cmdCreateTempConf;

    @Value("${telegraf.conf.cmd.renew}")
    private String cmdRenewConf;

    @Value("${telegraf.cmd.restart}")
    private String cmdRestartTelegraf;

    @Value("${telegraf.jolokia2_agent.urls.template}")
    private String urlsTemplate;

    /*@Value("${docker.build}")
    private boolean dockerBuild;

    @Value("${docker.host.ip}")
    private String dockerHostIp;*/

    public String getEurekaApi() {
        return eurekaApi;
    }

    public void setEurekaApi(String eurekaApi) {
        this.eurekaApi = eurekaApi;
    }

    public String getConfTempFile() {
        return confTempFile;
    }

    public void setConfTempFile(String confTempFile) {
        this.confTempFile = confTempFile;
    }

    public String getCmdCreateTempConf() {
        return cmdCreateTempConf;
    }

    public void setCmdCreateTempConf(String cmdCreateTempConf) {
        this.cmdCreateTempConf = cmdCreateTempConf;
    }

    public String getCmdRenewConf() {
        return cmdRenewConf;
    }

    public void setCmdRenewConf(String cmdRenewConf) {
        this.cmdRenewConf = cmdRenewConf;
    }

    public String getCmdRestartTelegraf() {
        return cmdRestartTelegraf;
    }

    public void setCmdRestartTelegraf(String cmdRestartTelegraf) {
        this.cmdRestartTelegraf = cmdRestartTelegraf;
    }

    public String getUrlsTemplate() {
        return urlsTemplate;
    }

    public void setUrlsTemplate(String urlsTemplate) {
        this.urlsTemplate = urlsTemplate;
    }

    /*public boolean isDockerBuild() {
        return dockerBuild;
    }

    public void setDockerBuild(boolean dockerBuild) {
        this.dockerBuild = dockerBuild;
    }

    public String getDockerHostIp() {
        return dockerHostIp;
    }

    public void setDockerHostIp(String dockerHostIp) {
        this.dockerHostIp = dockerHostIp;
    }*/
}
