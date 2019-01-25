package com.mycurdpro.common.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.io.Files;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.render.JsonRender;
import com.jfinal.upload.UploadFile;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.PermissionInterceptor;
import com.mycurdpro.common.interceptor.SearchSql;
import com.mycurdpro.common.utils.Id.IdUtils;
import com.mycurdpro.system.model.SysFile;
import com.mycurdpro.system.model.SysOrg;
import com.mycurdpro.system.model.SysRole;
import com.mycurdpro.system.model.SysUser;
import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

/**
 * 工具controller
 */
@Clear(PermissionInterceptor.class)
public class UtilsController extends BaseController {
    /**
     * 上传文件
     */
    private final static Prop prop = PropKit.use("file.properties");

    /**
     * 生成 图像验证码
     */
    public void captcha() {
        renderCaptcha();
    }



    /**
     * 生成 二维码
     */
    public void qrcode() {
        String content = getPara("txt", "my_curd_pro very great");
        Integer width = getParaToInt("width", 200);
        Integer height = getParaToInt("height", 200);
        renderQrCode(content, width, height);
    }

    /**
     * 跳转到上传文件页面
     */
    public void goUploadFilePage() {
        String uploadUrl = getPara("uploadUrl");
        String label = getPara("label");
        if (StringUtils.isEmpty(uploadUrl)) {
            setAttr("msg", "uploadUrl参数不可为空");
            render("common/card.ftl");
            return;
        }
        setAttr("uploadUrl", uploadUrl);
        setAttr("label", label);
        render("common/utils/uploadFile.ftl");
    }

    /**
     * 系统用户信息
     */
    public void userInfo() {
        String username = getPara("username");
        if (StringUtils.notEmpty(username)) {
            SysUser sysUser = SysUser.dao.findInfoByUsername(username);
            setAttr("sysUser", sysUser);
        }
        setAttr("username", username);

        render("common/utils/userInfo.ftl");
    }

    /**
     * 组织机构信息
     */
    public void orgInfo() {
        String orgId = getPara("id");
        if (StringUtils.notEmpty(orgId)) {
            SysOrg sysOrg = SysOrg.dao.findById(orgId);
            setAttr("sysOrg", sysOrg);
        }
        setAttr("orgId", orgId);
        render("common/utils/orgInfo.ftl");
    }

    /**
     * 角色选择
     */
    public void role() {
        String singleSelect = getPara("singleSelect", "false");
        setAttr("singleSelect", singleSelect);
        setAttr("yesBtnTxt", getPara("yesBtnTxt", "添加角色"));
        render("common/utils/role.ftl");
    }

    /**
     * 角色选择 数据
     */
    @SuppressWarnings("Duplicates")
    @Before(SearchSql.class)
    public void queryRole() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysRole> sysRolePage = SysRole.dao.page(pageNumber, pageSize, where);
        renderDatagrid(sysRolePage);
    }

    /**
     * 用户选择
     */
    public void user() {
        String singleSelect = getPara("singleSelect", "false");
        setAttr("singleSelect", singleSelect);
        setAttr("yesBtnTxt", getPara("yesBtnTxt", "添加用户"));
        render("common/utils/user.ftl");
    }

    /**
     * 用户选择 数据
     */
    @SuppressWarnings("Duplicates")
    @Before(SearchSql.class)
    public void queryUser() {
        int pageNumber = getAttr("pageNumber");
        int pageSize = getAttr("pageSize");
        String where = getAttr(Constant.SEARCH_SQL);
        Page<SysUser> sysUserPage = SysUser.dao.page(pageNumber, pageSize, where);
        renderDatagrid(sysUserPage);
    }

    @SuppressWarnings("Duplicates")
    public void uploadFile() throws IOException {
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", prop.get("imageType"));
        extMap.put("media", prop.get("mediaType"));
        extMap.put("office", prop.get("officeType"));
        extMap.put("file", prop.get("fileType"));
        String limitFileTypes = extMap.get("image") + "," + extMap.get("media") + "," + extMap.get("office") + "," + extMap.get("file");
        // 文件为空
        UploadFile uploadFile = getFile("file");
        if (uploadFile == null) {
            Ret ret = Ret.create().setFail().set("msg", "请选择文件");
            render(new JsonRender(JSON.toJSONString(ret)).forIE());
            return;
        }

        String orginFilename = uploadFile.getOriginalFileName();     // 文件原名
        String fileSuf = FileUtils.getExtensionName(orginFilename);  // 文件后缀
        // 文件类型非法
        if (!Arrays.asList(limitFileTypes.split(",")).contains(fileSuf)) {
            uploadFile.getFile().delete();
            String errMsg = "只允许后缀为:<br/>" + extMap.get("image")
                    + "<br/>" + extMap.get("media")
                    + "<br/>" + extMap.get("office")
                    + "<br/>" + extMap.get("file")
                    + "<br/>格式文件";
            Ret ret = Ret.create().setFail().set("msg", errMsg);
            render(new JsonRender(JSON.toJSONString(ret)).forIE());
            return;
        }

        // 文件保存
        String pre = "/" + new DateTime(new Date()).toString("yyyyMMdd");
        if (Arrays.asList(extMap.get("image").split(",")).contains(fileSuf)) {
            pre = prop.get("imagePath") + pre;
        } else if (Arrays.asList(extMap.get("media").split(",")).contains(fileSuf)) {
            pre = prop.get("mediaPath") + pre;
        } else if (Arrays.asList(extMap.get("office").split(",")).contains(fileSuf)) {
            pre = prop.get("officePath") + pre;
        } else if (Arrays.asList(extMap.get("file").split(",")).contains(fileSuf)) {
            pre = prop.get("filePath") + pre;
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
        String newFileName = new DateTime(new Date()).toString("HHmmssS") + RandomUtils.number(10000) + "." + fileSuf;
        String relativePath = pre + "/" + newFileName;
        File savefile = new File(PathKit.getWebRootPath() + "/" + relativePath);
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
            Ret ret = Ret.create().setOk()
                    .set("msg", "上传成功")
                    .set("path", sysFile.getPath())
                    .set("id", sysFile.getId());
            render(new JsonRender(JSON.toJSONString(ret)).forIE());
        }
    }

}
