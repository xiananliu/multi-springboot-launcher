package io.github.xiananliu.msl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {


    public static void main(String[] args) throws Exception {
        log.info("欢迎使用 citybase 多服务引导程序");
        Config config = YmlLoader.loadFile("./config.yml", Config.class);
        if (config == null) {
            log.info("当前目录下没有config.yml");
            return;
        }
        log.info("读取配置:{}", config);
        for (Config.Item each : config.getServices()) {
            Launcher.load(each.getClasspath(), each.getArgs());
        }
    }


}
