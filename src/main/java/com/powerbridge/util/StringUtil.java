package com.powerbridge.util;

/**
 * Created by shindoyang on 2018/9/28.
 */
public class StringUtil {
    /**
     * 替换字符串中的ip，此方法不通用
     * @author      shindo
     * @param
     * @return
     * @date        2018/9/28 21:05
     * @exception
     */
    public static String replaceIp(String org, String target){
        String[] split = org.split(":");
        StringBuffer sb = new StringBuffer();
        sb.append(split[0]).append(":").append(target).append(":").append(split[2]);
        return sb.toString();
    }
}
