package com.powerbridge;

import com.powerbridge.util.FileUtil;

/**
 * Created by shindoyang on 2018/9/27.
 */
public class FileReplaceTest {
    public static void main(String[] args) {
        /*String temp = "temp:192.168.32.136:8762";
        System.out.println(temp.substring(temp.indexOf(":")+1,temp.length()));*/

        //#   urls = ["http://localhost:8080/jolokia"]
        //urls = ["http://localhost:8180/actuator/jolokia","http://localhost:8762/actuator/jolokia"]

        String filePath = "D:\\telegraf.conf";
        String[] orgStr = new String[]{"temp:192.168.32.136:8762","sap:192.168.32.137:8744"};
        StringBuffer sb = new StringBuffer();
        sb.append("#   urls = [\"http://localhost:8080/jolokia\"] \r\n   urls = [");
        for(int i = 0; i< orgStr.length;i++){
            String org = orgStr[i];
            if(i==orgStr.length-1) {
                sb.append("\"http://" + org.substring(org.indexOf(":") + 1, org.length())).append("/actuator/jolokia\"");
            }else{
                sb.append("\"http://" + org.substring(org.indexOf(":") + 1, org.length())).append("/actuator/jolokia\",");
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
        String orgTemplate = "#   urls = [\"http://localhost:8080/jolokia\"]";
        FileUtil.replaceFileStr(filePath, orgTemplate, sb.toString());
    }
}
