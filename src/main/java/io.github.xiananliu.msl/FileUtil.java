package io.github.xiananliu.msl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.function.Consumer;

public class FileUtil {


    /**
     * 文件夹扫描文件
     * @param source
     * @param consumer
     */
    public static void scanAllFiles(Path source, Consumer<File> consumer) {

        if (!source.toFile().isDirectory()) {
            return;
        }

        for (File file : source.toFile().listFiles()) {
            if (file.isDirectory()) {
                FileUtil.scanAllFiles(file.getAbsoluteFile().toPath(), consumer);
                continue;
            }
            consumer.accept(file);
        }
    }


    /**
     * 从文件夹拷贝到文件夹
     * @param source
     * @param target
     * @throws IOException
     */
    public static void filesCopyFromDir(Path source, Path target) throws IOException {

        if (!source.toFile().isDirectory()) {
            return;
        }

        scanAllFiles(source, (File file) -> {
            if (Objects.equals(file.getName(), ".wh..wh..opq")) {
                return;
            }
            Path relativize = source.relativize(file.toPath());
            Path targetPath = target.resolve(relativize);
            targetPath.getParent().toFile().mkdirs();
            try {
                Files.copy(file.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


    /**
     * 创建文件并写入数据
     *
     * @param content 写入的内容
     * @param file    文件
     */
    public static void createFileWithContent(String content, File file) throws IOException {
        if (file.exists()) {
            throw new RuntimeException("file already exists");
        }

        file.createNewFile();
        append(content, file);
    }

    /**
     * 向文件追加内容
     *
     * @param line 写入的内容
     * @param file 文件
     */
    public static void appendLn(String line, File file) {
        append(line, file);
        append("\n", file);
    }

    /**
     * 向文件追加内容
     *
     * @param line 写入的内容
     * @param file 文件
     */
    public static void append(String line, File file) {

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        try {
            if (!file.exists()) {
                boolean hasFile = file.createNewFile();
                if (hasFile) {
                    System.out.println("file not exists, create new file");
                }
                fos = new FileOutputStream(file);
            } else {
                fos = new FileOutputStream(file, true);
            }

            osw = new OutputStreamWriter(fos, "utf-8");
            // 写入内容
            osw.write(line);
        } catch (Exception e) {
            System.out.println("写入文件发生异常");
        } finally {
            // 关闭流
            try {
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                System.out.println("关闭流异常");
            }
        }

    }


}
