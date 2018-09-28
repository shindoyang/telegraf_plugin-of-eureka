package com.powerbridge.util;

import com.powerbridge.config.SupportConf;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * java在linux环境下执行linux命令，然后返回命令返回值。
 * @author shindo
 */
public class ExecLinuxCMD {

    Log log = LogFactory.getLog(ExecLinuxCMD.class);

    private SupportConf conf;

    public ExecLinuxCMD() {
    }

    public ExecLinuxCMD(SupportConf conf) {
        this.conf = conf;
    }

    public void createTempFile(){
        exec(conf.getCmdCreateTempConf());
        log.info("成功创建telegraf.temp文件！");
    }

    public void reNewConfFile(){
        exec(conf.getCmdRenewConf());
        log.info("成功将telegraf.temp替换为telegraf.conf！");
    }

    public void restartTelegraf(){
        exec(conf.getCmdRestartTelegraf());
        log.info("成功重启telegraf服务！");
    }

    private static void exec(String cmd) {
        Runtime run = Runtime.getRuntime();//返回与当前 Java 应用程序相关的运行时对象
        try {
            Process p = run.exec(cmd);// 启动另一个进程来执行命令
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
            String lineStr;
            while ((lineStr = inBr.readLine()) != null)
                //获得命令执行后在控制台的输出信息
                System.out.println(lineStr);// 打印输出信息
            //检查命令是否执行失败。
            if (p.waitFor() != 0) {
                if (p.exitValue() == 1)//p.exitValue()==0表示正常结束，1：非正常结束
                    System.err.println("命令执行失败!");
            }
            inBr.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
