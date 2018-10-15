## 前言
要搭建一套能监控服务器、docker、docker容器、jvm的APM服务，还要有阀值预警功能。  
初步研究，发现SpringBootAdmin 和 docker自带的监控都不太满足，最后选定AJTIG技术方案。  
>telegraf已自带docker脚本插件，但是只能拉取docker及其容器的基础信息，并不能拉取容器内应用的jvm信息  
所以要监控应用jvm，还得配合actuator和jolokia  
AJTIG: actuator + jolokia + telegraf + influxdb + grafana  

目前telegraf的确已经支持多应用监控，比如inputs.jolokia_agent 可以这样配置来监控IP_1:port_1和IP_2:port_2对应的两个应用 
urls = ["http://IP_1:port_1/actuator/jolokia","http://IP_2:port_2/actuator/jolokia"]
但是聪明如你，肯定也发现了这套方案的痛点：  

是的，telegraf并没有支持springcloud注册中心的脚本插件，这就意味着，如果我使用了这套方案，必须在新增一个应用之后手动修改
/etc/telegraf/telegraf.conf里的jolokia配置，加上新增应用的ip/port。可以想象，如果应用数量一上来，对于维护人员来说就是一场灾难。  
也发现有人和我遇到一样的问题：https://github.com/influxdata/telegraf/issues/3005

本来，我是想着把go语言学好，然后打个分支，看能不能给telegraf贡献个支持eureka的插件。奈何时间上不现实，只好用java写个功能相仿的插件：
>插件需指定eureka的访问地址，以便调用其api拉取注册中心的所有应用的ip/port  
1. 插件启动时默认将eureka中所有已注册的服务同步到telegraf.conf的jolokia脚本上
2. 每5分钟(可调整)监听eureka中注册列表的变化，如果有新增就同步到telegraf.conf脚本上

## 启动方法：

把 application-{env}.properties 与 项目jar(telegrafsupport-0.0.1-SNAPSHOT.jar)包放在同一目录下，执行以下命令启动：  
nohup java -jar telegrafsupport-0.0.1-SNAPSHOT.jar --spring.profiles.active=test  >myserver.log 2>&1 &



