package com.mycurdpro.common.utils;

import com.jfinal.aop.Clear;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.interceptor.PermissionInterceptor;
import com.mycurdpro.system.model.SysOrg;
import com.mycurdpro.system.model.SysUser;

/**
 * 工具controller
 */
@Clear(PermissionInterceptor.class)
public class UtilsController extends BaseController {
    /**
     * 系统用户信息
     */
    public void userInfo(){
        String username = getPara("username");
        if(StringUtils.notEmpty(username)){
            SysUser sysUser = SysUser.dao.findInfoByUsername(username);
            setAttr("sysUser",sysUser);
        }
        setAttr("username",username);

        render("common/utils/userInfo.ftl");
    }

    public void orgInfo(){
        String orgId = getPara("id");
        if(StringUtils.notEmpty(orgId)){
            SysOrg sysOrg = SysOrg.dao.findById(orgId);
            setAttr("sysOrg",sysOrg);
        }
        setAttr("orgId",orgId);
        render("common/utils/orgInfo.ftl");
    }
}
