package com.powerbridge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shindoyang on 2018/9/25.
 */
public class ListTest {
    public static void main(String[] args) {
        testChange();
    }


    /**
     * 测试list的clear()方法
     * @author      shindo
     * @param       []
     * @return      void
     * @date        2018/9/25 15:02
     * @exception
     */
    public static void testClear(){
        List<String> list = new ArrayList<>();
        list.add("111111");
        list.add("222222");
        list.add("333333");

        System.out.println(list.size());
        for(String temp : list){
            System.out.println(temp);
        }

        list.clear();

        System.out.println(list.size());
    }

    public static void testChange(){
        List<String> list = new ArrayList<>();
        list.add("111111");
        list.add("222222");
        list.add("333333");

        List<String> list2 = new ArrayList<>();
        list2.add("111111");
        list2.add("222222");
        list2.add("333333");

        boolean reNew = false;
        if(list2.size() > list.size()){
            System.out.println("当前注册服务数量多于系统记录的服务数，重置telegraf.conf");

        }else{
            for(String currentRegisterProject : list2){
                boolean hasExist = false;
                for(String registerPro : list){
                    if(currentRegisterProject.equals(registerPro)){
                        hasExist = true;
                        break;
                    }
                }
                if(!hasExist){
                    reNew = true;
                }
            }
            if(reNew){
                System.out.println("跑批发现新注册的服务，更新telegraf配置文件");
            }else{
                System.out.println("跑批没有发现注册列表变动");
            }
        }

    }

}
