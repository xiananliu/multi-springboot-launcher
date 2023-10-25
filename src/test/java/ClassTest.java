
import io.github.xiananliu.msl.FileUtil;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ClassTest {

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {

        String dirPath = "/Users/xiananliu/IdeaProjects/abc/workspace/deh/app"
                + "/project-account-mgr-svc-b20ec89e06b83949/project-account-mgr-svc-bootstrap-0.0.1-SNAPSHOT";

        List<URL> urlList = new ArrayList<>();
        urlList.add(new File(dirPath).toURI().toURL());
        FileUtil.scanAllFiles(Paths.get(dirPath), file -> {
            if (file.getName().endsWith(".jar")) {
                try {
                    urlList.add(file.toURI().toURL());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

        URLClassLoader urlClassLoader = new URLClassLoader(urlList.toArray(new URL[0]),null);
        Class<?> cl = urlClassLoader.loadClass("org.springframework.cloud.consul.ConsulAutoConfiguration");
        System.out.println(cl);


    }
}
