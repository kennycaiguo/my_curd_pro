package com.mycurdpro.common.utils;

import com.jfinal.aop.Clear;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.interceptor.PermissionInterceptor;
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
}
