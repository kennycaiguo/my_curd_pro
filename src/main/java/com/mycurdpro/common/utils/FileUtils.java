package com.mycurdpro.common.utils;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件操作工具类, 依赖 google guava
 *
 * @author zhangchuang
 */
@SuppressWarnings("UnstableApiUsage")
public class FileUtils {

    private final static Logger LOG = LoggerFactory.getLogger(FileUtils.class);


    /**
     * 读文件为文本
     *
     * @param filePath 文件路径
     * @return 文件文本内容
     * @throws IOException 读文件异常
     */
    public static String readFile(String filePath) throws IOException {
        LOG.debug("read file path: {}", filePath);
        return Files.asCharSource(new File(filePath), Charsets.UTF_8).read();
    }


    /**
     * 文本内容写入文件
     *
     * @param content  文本内容
     * @param savePath 写文件路劲
     * @throws IOException 写文件异常
     */
    public static void writeFile(String content, String savePath) throws IOException {
        LOG.debug("write file path: {}", savePath);
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {
            Files.createParentDirs(saveFile);
        }
        Files.write(content.getBytes(), saveFile);
    }


    /**
     * 通过文件全路径 获得文件的 MIME(contentType)类型
     * jdk7
     *
     * @param absolutePath 文件全路径
     * @return
     */
    public static String getMime(String absolutePath) {
        Path path = Paths.get(absolutePath);
        String contentType = null;
        try {
            contentType = java.nio.file.Files.probeContentType(path);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return contentType;
    }

    /**
     * 获取文件 后缀
     *
     * @param filename 文件名
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }
}
