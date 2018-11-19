package com.mycurdpro.common.utils;

import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    private final static Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 写文件
     *
     * @param content
     * @param filePath
     */
    public static void writeFile(String content, String filePath) {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                Files.createParentDirs(f);
            }
            Files.write(content.getBytes(), new File(filePath));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
