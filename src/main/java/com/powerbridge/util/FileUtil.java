package com.powerbridge.util;

import com.powerbridge.config.SupportConf;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.List;

public class FileUtil {
    private Log log = LogFactory.getLog(FileUtil.class);

    private SupportConf conf;

    public FileUtil() {
    }

    public FileUtil(SupportConf conf) {
        this.conf = conf;
    }

    /**
     * 将eureka中注册的所有服务新增到telegraf.temp文件中
     * @author      shindo
     * @param       []
     * @return      void
     * @date        2018/9/21 15:05
     * @exception   
     */
    public void reWriteConf(List<String> projectInfos) throws Exception{
        String filePath = conf.getConfTempFile();
        StringBuffer sb = new StringBuffer();
        sb.append(conf.getUrlsTemplate() + " \r\n   urls = [");
        for(int i = 0; i< projectInfos.size();i++){
            String projectInfo = projectInfos.get(i);
            //如果是docker容器中部署的应用，需要替换为宿主机的ip
            /*if(conf.isDockerBuild())
                projectInfo = StringUtil.replaceIp(projectInfo, conf.getDockerHostIp());*/
            sb.append("\"http://" + projectInfo.substring(projectInfo.indexOf(":") + 1, projectInfo.length())).append("/actuator/jolokia\"");
            if(i!=projectInfos.size()-1) {
                sb.append(",");
            }
        }
        sb.append("]");
        log.info(sb.toString());
        replaceFileStr(filePath, conf.getUrlsTemplate(), sb.toString());
        log.info("成功将eureka中注册的服务写入telegraf.temp文件！");
    }

    /***
     * 替换指定文件中的指定内容
     * @param filepath  文件路径
     * @param sourceStr 文件需要替换的内容
     * @param targetStr 替换后的内容
     * @return 替换成功返回true，否则返回false
     */
    public static boolean replaceFileStr(String filepath,String sourceStr,String targetStr){
        try {
            FileReader fis = new FileReader(filepath);  // 创建文件输入流
            BufferedReader br = new BufferedReader(fis);
            char[] data = new char[1024];               // 创建缓冲字符数组
            int rn = 0;
            StringBuilder sb=new StringBuilder();       // 创建字符串构建器
            //fis.read(data)：将字符读入数组。在某个输入可用、发生 I/O 错误或者已到达流的末尾前，此方法一直阻塞。读取的字符数，如果已到达流的末尾，则返回 -1
            while ((rn = fis.read(data)) > 0) {         // 读取文件内容到字符串构建器
                String str=String.valueOf(data,0,rn);//把数组转换成字符串
                //System.out.println(str);
                sb.append(str);
            }
            fis.close();// 关闭输入流
            // 从构建器中生成字符串，并替换搜索文本
            String str = sb.toString().replace(sourceStr, targetStr);
            FileWriter fout = new FileWriter(filepath);// 创建文件输出流
            fout.write(str.toCharArray());// 把替换完成的字符串写入文件内
            fout.close();// 关闭输出流

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    // 删除某个目录及目录下的所有子目录和文件
    public static boolean deleteDir(File dir) {
        // 如果是文件夹
        if (dir.isDirectory()) {
            // 则读出该文件夹下的的所有文件
            String[] children = dir.list();
            // 递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                // File f=new File（String parent ，String child）
                // parent抽象路径名用于表示目录，child 路径名字符串用于表示目录或文件。
                // 连起来刚好是文件路径
                boolean isDelete = deleteDir(new File(dir, children[i]));
                // 如果删完了，没东西删，isDelete==false的时候，则跳出此时递归
                if (!isDelete) {
                    return false;
                }
            }
        }
        // 读到的是一个文件或者是一个空目录，则可以直接删除
        return dir.delete();
    }

}
