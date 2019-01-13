package com.mycurdpro.common.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ZipUtilsTest {

    @Test
    public void toZip() throws FileNotFoundException {
        // 通过文本 文件名生成压缩文件
        String fileStr1 = "my name is zhangchuang";
        String fileName1 = "com/mycurd/one.txt";      // 多级目录
        String fileStr2 = "I am a man 25 years old";
        String fileName2 = "com/mycurd/hello/two.txt";
        List<String> datas = new ArrayList<>();
        datas.add(fileStr1);
        datas.add(fileStr2);

        List<String> files = new ArrayList<>();
        files.add(fileName1);
        files.add(fileName2);

        File zipFile = new File("D:/test.zip");
        OutputStream os = new FileOutputStream(zipFile);
        ZipUtils.toZip(datas,files,os);

        Assert.assertTrue(zipFile.exists());
    }
}