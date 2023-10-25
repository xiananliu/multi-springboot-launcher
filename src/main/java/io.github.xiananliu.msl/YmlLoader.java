package io.github.xiananliu.msl;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class YmlLoader {
    public static <T> T loadFile(String path, Class<T> clazz) {
        Yaml yaml = new Yaml();
        try {
            InputStream inputStream = new FileInputStream(path);
            return yaml.loadAs(inputStream, clazz);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}