package com.mycurdpro.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.PermissionInterceptor;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.validator.IdRequired;
import com.mycurdpro.system.model.SysDataRegion;

import java.util.List;

/**
 * 地区数据
 */
public class SysDataRegionController extends BaseController {
    public void index() {
        render("system/sysDataRegion.ftl");
    }

    public void query() {
        // 初始传递pid 为 0
        String pid = getPara("id", "0");
        List<SysDataRegion> sysDataRegionList = SysDataRegion.dao.findByPid(pid);
        for (SysDataRegion dataRegion : sysDataRegionList) {
            // 层级别为 3 为县区，没有更细的节点
            if (!"3".equals(dataRegion.getLevelType())) {
                dataRegion.put("state", "closed");
            }
            // 字体图标
            dataRegion.put("iconCls", "iconfont");
        }
        renderJson(sysDataRegionList);
    }

    public void newModel() {
        String id = getPara("id");
        if (StringUtils.notEmpty(id)) {
            SysDataRegion sysDataRegion = SysDataRegion.dao.findById(id);
            setAttr("sysDataRegion", sysDataRegion);
            setAttr("pid", sysDataRegion.getParentId());
        } else {
            setAttr("pid", getPara("pid"));
        }
        render("system/sysDataRegion_form.ftl");
    }

    public void addOrUpdateAction() {
        SysDataRegion sysDataRegion = getBean(SysDataRegion.class, "");
        SysDataRegion sysDataRegionOld = SysDataRegion.dao.findById(sysDataRegion.getId());
        if (sysDataRegionOld == null) {
            boolean saveFlag = sysDataRegion.save();
            if (saveFlag) {
                renderSuccess(Constant.ADD_SUCCESS);
            } else {
                renderFail(Constant.ADD_FAIL);
            }
        } else {
            // 存在数据 更新
            boolean updateFlag = sysDataRegion.update();
            if (updateFlag) {
                renderSuccess(Constant.UPDATE_SUCCESS);
            } else {
                renderSuccess(Constant.UPDATE_FAIL);
            }
        }
    }

    @Before(IdRequired.class)
    public void deleteAction() {
        String id = getPara("id");
        SysDataRegion sysDataRegion = SysDataRegion.dao.findById(id);
        if (sysDataRegion == null) {
            renderFail(Constant.DELETE_FAIL);
            return;
        }

        // 不级联删除
        if (sysDataRegion.delete()) {
            renderSuccess(Constant.DELETE_SUCCESS);
        } else {
            renderFail(Constant.DELETE_FAIL);
        }
    }


    /**
     * 其它业务使用
     */
    @Clear(PermissionInterceptor.class)
    public void data() {
        String pid = getPara(0, "100000");
        String sql = "select ID,NAME,SHORT_NAME,LNG,LAT,SUBSTR(pinyin, 0, 1) as P1 from sys_data_region where parent_id = ? ORDER by ID";
        List<SysDataRegion> sysDataRegionList = SysDataRegion.dao.find(sql, pid);
        renderJson(sysDataRegionList);
    }
}
