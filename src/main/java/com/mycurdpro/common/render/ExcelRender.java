package com.mycurdpro.common.render;

import com.jfinal.render.Render;
import com.jfinal.render.RenderException;
import com.mycurdpro.common.utils.WebUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;

/**
 * excel 导出, 通过 POI 工具
 *
 * @author zhangchuang
 */
@SuppressWarnings("unused")
public class ExcelRender extends Render {

    private final static String CONTENT_TYPE = "application/msexcel;charset=" + getEncoding();
    private final static Logger LOG = LoggerFactory.getLogger(ExcelRender.class);

    private String fileName;
    private Workbook workbook;

    private ExcelRender(Workbook workbook) {
        this.workbook = workbook;
    }

    public static ExcelRender me(Workbook workbook) {
        return new ExcelRender(workbook);
    }

    public ExcelRender fileName(String fileName) {
        this.fileName = fileName;
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
            workbook.write(os);
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
