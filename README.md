>Spring程序会按优先级从下面这些路径来加载application.properties配置文件

1. 当前目录下的/config目录
2. 当前目录
3. classpath里的/config目录
4. classpath 跟目录

>指定程序运行环境，可手动在启动命令添加指令实现： --spring.profiles.active={环境,注意等号前后不能有空格}


##启动 telegrafsupport 插件程序

把 application.properties 与 telegrafsupport-0.0.1-SNAPSHOT.jar 包放在同一目录下，执行以下命令启动：

java -jar telegrafsupport-0.0.1-SNAPSHOT.jar --spring.profiles.active={环境test/prod}
