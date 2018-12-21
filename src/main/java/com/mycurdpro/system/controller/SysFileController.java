package com.mycurdpro.system.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.io.Files;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.render.JsonRender;
import com.jfinal.upload.UploadFile;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.PermissionInterceptor;
import com.mycurdpro.common.interceptor.SearchSql;
import com.mycurdpro.common.utils.FileUtils;
import com.mycurdpro.common.utils.Id.IdUtils;
import com.mycurdpro.common.utils.RandomUtils;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.utils.WebUtils;
import com.mycurdpro.system.model.SysFile;
import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 用户文件
 * 具有 admin 角色的用户可以查看所有文件
 * 普通用户只可以查看自己的文件
 */
public class SysFileController extends BaseController {

    private final static Prop prop = PropKit.use("file.properties");

    /**
     * 列表页
     */
    public void index() {
        String username = WebUtils.getSessionUsername(this);
        setAttr("showUserSearch", "admin".equals(username));
        render("system/sysFile.ftl");
    }


    /**
     * 列表数据
     */
    @Before(SearchSql.class)
    public void query() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String sort = getPara("sort");
        String order = getPara("order");
        String where = getAttr(Constant.SEARCH_SQL);

        String username = WebUtils.getSessionUsername(this);
        if(!"admin".equals(username)){
            if(StringUtils.isEmpty(where)){
                where = " su.username = '"+username+"' ";
            }else{
                where += " and  su.username = '"+username+"' ";
            }
        }

        Page<SysFile> sysFilePage = SysFile.dao.page(pageNumber, pageSize, sort, order, where);
        renderDatagrid(sysFilePage);
    }


    /**
     * 打开新增或者修改弹出框
     */
    public void newModel() {
        String id = getPara("id");
        if (id != null) {
            SysFile sysFile = SysFile.dao.findById(id);
            setAttr("sysFile", sysFile);
        }
        render("system/sysFile_form.ftl");
    }


    /**
     * 增加
     */
    @Clear(PermissionInterceptor.class)
    public void addAction() throws IOException {
        HashMap<String, String> extMap = new HashMap<String, String>();

        extMap.put("image", prop.get("imageType"));
        extMap.put("media", prop.get("mediaType"));
        extMap.put("office", prop.get("officeType"));
        extMap.put("file", prop.get("fileType"));
        String limitFileTypes = extMap.get("image") + "," + extMap.get("media")+ "," + extMap.get("office") + "," + extMap.get("file");

        // 文件为空
        UploadFile uploadFile = getFile("file");
        if(uploadFile==null){
            Ret ret = Ret.create().setFail().set("msg", "请选择文件");
            render(new JsonRender(JSON.toJSONString(ret)).forIE());
            return;
        }

        String orginFilename = uploadFile.getOriginalFileName();     // 文件原名
        String fileSuf = FileUtils.getExtensionName(orginFilename);  // 文件后缀

        // 文件类型非法
        if (!Arrays.asList(limitFileTypes.split(",")).contains(fileSuf)) {
            uploadFile.getFile().delete();
            String errMsg =  "只允许后缀为:<br/>" + extMap.get("image")
                    + "<br/>" + extMap.get("media")
                    + "<br/>" + extMap.get("office")
                    + "<br/>" + extMap.get("file")
                    + "<br/>格式文件";
            Ret ret = Ret.create().setFail().set("msg", errMsg);
            render(new JsonRender(JSON.toJSONString(ret)).forIE());
            return;
        }

        // 文件保存
        String pre = "/"+new DateTime(new Date()).toString("yyyyMMdd");
        if (Arrays.asList(extMap.get("image").split(",")).contains(fileSuf)) {
            pre = prop.get("imagePath")+pre;
        } else if (Arrays.asList(extMap.get("media").split(",")).contains(fileSuf)) {
            pre = prop.get("mediaPath")+pre;
        }else if(Arrays.asList(extMap.get("office").split(",")).contains(fileSuf)){
            pre = prop.get("officePath")+pre;
        }else if (Arrays.asList(extMap.get("file").split(",")).contains(fileSuf)) {
            pre = prop.get("filePath")+pre;
        } else {
            String errMsg = "只允许后缀为:<br/>" + extMap.get("image")
                    + "<br/>" + extMap.get("media")
                    + "<br/>" + extMap.get("office")
                    + "<br/>" + extMap.get("file")
                    + "<br/>格式文件";
            uploadFile.getFile().delete();
            Ret ret = Ret.create().setFail().set("msg", errMsg);
            render(new JsonRender(JSON.toJSONString(ret)).forIE());
            return;
        }
        // 时分秒毫秒+随机数
        String newFileName = new DateTime(new Date()).toString("HHmmssS")+ RandomUtils.number(10000) + "." + fileSuf;
        String relativePath = pre + "/" + newFileName;
        File savefile = new File(PathKit.getWebRootPath() +"/"  + relativePath);
        if (!savefile.exists()) {
            Files.createParentDirs(savefile);
        }
        Files.copy(uploadFile.getFile(), savefile);
        if (uploadFile.getFile().exists()) {
            uploadFile.getFile().delete();
        }

        // 保存数据库记录
        SysFile sysFile = new SysFile();
        sysFile.setId(IdUtils.id());
        sysFile.setCreater(WebUtils.getSessionUsername(this));
        sysFile.setCreateTime(new Date());
        sysFile.setOriName(orginFilename);
        sysFile.setPath(relativePath);
        sysFile.setMime(FileUtils.getMime(savefile.getAbsolutePath()));
        sysFile.setLength(BigDecimal.valueOf(savefile.length()));
        sysFile.setRemark(getPara("remark"));
        sysFile.setType(fileSuf);


        boolean saveFlag = sysFile.save();
        if (!saveFlag) {
            savefile.delete();
            Ret ret = Ret.create().setFail().set("msg", "上传失败");
            render(new JsonRender(JSON.toJSONString(ret)).forIE());
        } else {
            Ret ret = Ret.create().setOk().set("msg", "上传成功");
            render(new JsonRender(JSON.toJSONString(ret)).forIE());
        }
    }

    /**
     * 删除
     */
    @Before(Tx.class)
    public void deleteAction() {
        boolean opFlag = true;
        String ids = getPara("ids");
        List<SysFile> sysFiles;
        if (ids.contains(",")) {
            ids = ids.replaceAll(",", "','");
            ids = "'" + ids + "'";
            sysFiles = SysFile.dao.findByIds(ids);
        } else {
            SysFile sysFile = SysFile.dao.findById(ids);
            sysFiles = new ArrayList<>();
            sysFiles.add(sysFile);
        }

        // 记录删除
        for (SysFile sysFile : sysFiles) {
            opFlag = sysFile.delete();
            // 失败一次 立刻回滚（正常情况不该是异常)
            if (!opFlag) {
                throw new RuntimeException("删除文件异常，文件ids:" + ids);
            }
        }

        // 文件删除
        File tempFile;
        for (SysFile sysFile : sysFiles) {
            tempFile = new File(PathKit.getWebRootPath() + "/" + sysFile.getPath());
            if (tempFile.exists()) {
                tempFile.delete();
            }
        }
        renderSuccess(Constant.DELETE_SUCCESS);
    }

    /**
     * 修改
     */
    public void updateAction() {
        SysFile sysFile = getBean(SysFile.class, "");
        // TODO
        boolean updateFlag = sysFile.update();
        if (updateFlag) {
            renderSuccess(Constant.UPDATE_SUCCESS);
        } else {
            renderFail(Constant.UPDATE_FAIL);
        }
    }


    /**
     * 系统允许上传的文件类型，供combobox 使用
     */
    public void fileTypeData() {
        String imageTypes = prop.get("imageType");
        String mediaTypes = prop.get("mediaType");
        String officeType = prop.get("officeType");
        String fileTypes = prop.get("fileType");
        String[] types = (imageTypes + "," + mediaTypes + "," + officeType + "," + fileTypes).split(",");
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (String type : types) {
            Map<String, String> dataItem = new HashMap<String, String>();
            dataItem.put("label", type);
            dataItem.put("value", type);
            data.add(dataItem);
        }
        renderJson(data);
    }
}
