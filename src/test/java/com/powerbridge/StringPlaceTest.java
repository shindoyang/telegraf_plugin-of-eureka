package com.powerbridge;

import com.powerbridge.util.StringUtil;

/**
 * Created by shindoyang on 2018/9/28.
 */
public class StringPlaceTest {
    public static void main(String[] args) {
        String tempStr = "ACTUATOR-DEMO:192.168.32.136:8180";
        String replaceStr = "172.16.0.43";
        String result = StringUtil.replaceIp(tempStr,replaceStr);
        System.out.println(result);

    }
}
