import io.github.xiananliu.msl.Launcher;

public class Test {
    public static void main(String[] args) throws Exception {
        String[] args1 = new String[]{
                "--server.port=8080",
//                "--spring.jmx.default-domain=app1",
                "--spring.config.location=classpath:/application.yml,classpath:/application-bridge.yml,"
                        + "classpath:/application-extend.yml,/Users/xiananliu/app/common/conf/application.yml"
//
        };
        Launcher.load("/Users/xiananliu/IdeaProjects/abc/workspace/deh/app/app-abc/app-management-mgr-svc-bootstrap-0.0.1-SNAPSHOT", args1);

    }
}
