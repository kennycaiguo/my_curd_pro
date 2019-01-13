package com.mycurdpro.common.render;

import com.jfinal.render.Render;
import com.jfinal.render.RenderException;
import com.mycurdpro.common.utils.WebUtils;
import com.mycurdpro.common.utils.ZipUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


/**
 * zip render
 * 通过字符串数据 和 文件名 集合 下载 数据压缩文件
 *
 * @author chuang
 */
@SuppressWarnings("unused")
public class ZipRender extends Render {

    private final static Logger LOG = LoggerFactory.getLogger(ExcelRender.class);
    private final static String CONTENT_TYPE = "application/x-zip-compressed;charset=" + getEncoding();

    private String fileName;      // 下载文件名

    private List<String> datas;     // 字符串数据 集合
    private List<String> filenames; // 文件名 集合

    public static ZipRender me() {
        return new ZipRender();
    }

    public ZipRender fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public ZipRender datas(List<String> datas) {
        this.datas = datas;
        return this;
    }

    public ZipRender filenames(List<String> filenames) {
        this.filenames = filenames;
        return this;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void render() {
        response.reset();
        fileName = WebUtils.buildDownname(request, fileName);
        response.setHeader("Content-disposition", "attachment;" + fileName);
        response.setContentType(CONTENT_TYPE);
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            ZipUtils.toZip(datas, filenames, os);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RenderException(e);
        } finally {
            try {
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

}
