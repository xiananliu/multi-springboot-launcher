# multi-springboot-launcher
multi springboot application run on one jvm


让多个springboot 运行在同一个jvm 中
采用不同CLassloader 加载不同 springboot 程序技术，运行时隔离，达到节省内存的目的

启动前需要配置 config.yml， 格式如下
```
services:
  - classpath: /path/to/serviceA
    args:
      - arg1
      - arg2
  - classpath: /path/to/serviceB
    args:
      - arg3
      - arg4
```


1,classpath  指向jar 展开后的目录  
2,args 添加spring支持的任意参数 如 spring.profiles.active等
