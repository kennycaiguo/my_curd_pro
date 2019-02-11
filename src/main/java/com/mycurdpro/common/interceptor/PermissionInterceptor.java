package com.mycurdpro.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.JsonKit;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.system.model.SysMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 权限菜单 拦截器, 拦截页面
 * @author zhangchuang
 */
public class PermissionInterceptor implements Interceptor {
    private final static Logger LOG = LoggerFactory.getLogger(PermissionInterceptor.class);

    @Override
    public void intercept(Invocation inv) {
        String controllerKey = inv.getControllerKey();
        List<SysMenu> sysMenus = inv.getController().getSessionAttr(Constant.SYS_USER_MENU);

        LOG.debug("permission menu: {}", JsonKit.toJson(sysMenus));
        LOG.debug("controllerKey: {}", controllerKey);
        LOG.debug("actionKey: {}", inv.getActionKey());

        // 菜单只可配置 到 controller Key , 不可配置到 action key
        // 如果 需要控制 action key 访问权限，可在代码中 使用 RoleInterceptor
        for (SysMenu sysMenu : sysMenus) {
            if (!sysMenu.getUrl().equals("/") && sysMenu.getUrl().equals(controllerKey)) {
                LOG.debug("c: {}, a:{} 拥有 {}, 拥有菜单权限. ", controllerKey, inv.getActionKey(), sysMenu.getUrl());
                inv.invoke();
                return;
            }
        }

        // 无菜单权限
        BaseController baseController = (BaseController) inv.getController();
        baseController.addServiceLog("PermissionInterceptor: 访问无权限路径");
        inv.getController().render("/WEB-INF/views/common/no_permission.ftl");
    }
}
