package com.mycurdpro.common.utils.freemarker;

import com.mycurdpro.common.config.Constant;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;

/**
 * FreeMarker工具类
 */
public abstract class FreemarkerUtils {
    private final static Logger LOG = LoggerFactory.getLogger(FreemarkerUtils.class);

    /**
     * 通过模板文本 和 数据 得到 渲染后的内容
     *
     * @param templateContent 模板文件文本内容
     * @param paramMap        数据
     * @return
     */
    public static String renderAsText(String templateContent, Map<String, Object> paramMap) {
        StringWriter writer = new StringWriter();
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
            cfg.setTemplateLoader(new StringTemplateLoader(templateContent));
            cfg.setDefaultEncoding(Constant.DEFAULT_ENCODEING);
            Template template = cfg.getTemplate("");
            template.process(paramMap, writer);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return writer.toString();
    }

    /**
     * 通过模板和参数生成HTML文件
     *
     * @param tplDirectory 模板文件目录
     * @param tlName       模板文件名字
     * @param paramMap     数据
     * @param filePath     生成存放路径
     */
    public static void renderToFile(String tplDirectory, String tlName, Map<String, Object> paramMap, String filePath) {
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
            File file = new File(tplDirectory);// 模板目录
            configuration.setDirectoryForTemplateLoading(file);
            configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_22));
            Template template = configuration.getTemplate(tlName, Constant.DEFAULT_ENCODEING);

            File saveFile = new File(filePath); // 生成 的文件
            fileOutputStream = new FileOutputStream(saveFile);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, Constant.DEFAULT_ENCODEING);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            template.process(paramMap, bufferedWriter);
            bufferedWriter.flush();
            outputStreamWriter.close();
            fileOutputStream.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (null != fileOutputStream) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            if (null != outputStreamWriter) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }
}

class StringTemplateLoader implements TemplateLoader {
    private String template;

    public StringTemplateLoader(String template) {
        this.template = template;
        if (template == null) {
            this.template = "";
        }
    }

    public void closeTemplateSource(Object templateSource) {
        ((StringReader) templateSource).close();
    }

    public Object findTemplateSource(String name) {
        return new StringReader(template);
    }

    public long getLastModified(Object templateSource) {
        return 0;
    }

    public Reader getReader(Object templateSource, String encoding) {
        return (Reader) templateSource;
    }

}
