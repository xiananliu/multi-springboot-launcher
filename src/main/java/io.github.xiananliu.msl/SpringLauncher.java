package io.github.xiananliu.msl;

import org.springframework.boot.loader.JarLauncher;
import org.springframework.boot.loader.LaunchedURLClassLoader;
import org.springframework.boot.loader.archive.Archive;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class SpringLauncher extends JarLauncher {

    public SpringLauncher(Archive archive) {
        super(archive);
    }

    @Override
    protected void launch(String[] args) throws Exception {
        super.launch(args);
    }

    @Override
    protected ClassLoader createClassLoader(URL[] urls) throws Exception {
        return new LaunchedURLClassLoader(urls, null);
    }

    @Override
    protected void postProcessClassPathArchives(List<Archive> archives) throws Exception {
        //tsf 中有同名类，优先加载
        List<Archive> list = archives.stream().sorted((a,b)->{

            try {
                if (a.getUrl().getFile().contains("tsf")){
                    return -1;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return 1;
        }).collect(Collectors.toList());
        archives.clear();
        archives.addAll(list);

    }
}
