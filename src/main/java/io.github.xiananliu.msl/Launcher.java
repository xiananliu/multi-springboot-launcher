package io.github.xiananliu.msl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.loader.archive.Archive;
import org.springframework.boot.loader.archive.ExplodedArchive;
import org.springframework.boot.loader.archive.JarFileArchive;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Launcher {


    public static void load(String dirPath, String[] userArgs) throws Exception {
        log.info("正在启动:{},args:{}", dirPath, userArgs);
        clearFactory();
        List<String> args = new ArrayList<>(Arrays.asList(userArgs));
        String[] argss= args.toArray(new String[]{});
        SpringLauncher jarLauncher = new SpringLauncher(createArchive(dirPath));
        jarLauncher.launch(argss);
    }


    private static Archive createArchive(String path) throws IOException {
        File root = new File(path);
        if (!root.exists()) {
            throw new IllegalStateException("Unable to determine code source archive from " + root);
        } else {
            return (Archive)(root.isDirectory() ? new ExplodedArchive(root) : new JarFileArchive(root));
        }
    }


    private static void clearFactory() throws Exception {
        final Field factoryField = URL.class.getDeclaredField("factory");
        factoryField.setAccessible(true);
        final Field lockField = URL.class.getDeclaredField("streamHandlerLock");
        lockField.setAccessible(true);

        // use same lock as in java.net.URL.setURLStreamHandlerFactory
        synchronized (lockField.get(null)) {
            final URLStreamHandlerFactory urlStreamHandlerFactory = (URLStreamHandlerFactory) factoryField.get(null);
            // Reset the value to prevent Error due to a factory already defined
            factoryField.set(null, null);
//            URL.setURLStreamHandlerFactory(new AmazonS3UrlStreamHandlerFactory(urlStreamHandlerFactory));
        }
    }
}
