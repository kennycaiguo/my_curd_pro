package com.mycurdpro.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.system.model.SysMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 权限拦截器,菜单拦截器
 * @author zhangchuang
 */
public class PermissionInterceptor implements Interceptor {
    private final static Logger LOG = LoggerFactory.getLogger(PermissionInterceptor.class);

    @Override
    public void intercept(Invocation inv) {
        String controllerKey = inv.getControllerKey();
        List<SysMenu> sysMenus = inv.getController().getSessionAttr(Constant.SYS_USER_MENU);

        LOG.debug("permission menu: {}", JsonKit.toJson(sysMenus));
        LOG.debug("controllerKey: {}",controllerKey);
        LOG.debug("actionKey: {}",inv.getActionKey());

        for(SysMenu sysMenu : sysMenus){
            if (StringUtils.notEmpty(sysMenu.getUrl()) && !sysMenu.getUrl().equals("/") && sysMenu.getUrl().startsWith(controllerKey)) {
                LOG.debug("c: {}, a:{} 拥有 {}, 拥有权限. ",controllerKey,inv.getActionKey(),sysMenu.getUrl());
                inv.invoke();
                return;
            }
        }

        // 没有权限
        BaseController baseController = (BaseController) inv.getController();
        baseController.addServiceLog("访问无权限路径[" + inv.getActionKey() + "]");

        inv.getController().render("/WEB-INF/views/common/no_permission.ftl");
    }
}
