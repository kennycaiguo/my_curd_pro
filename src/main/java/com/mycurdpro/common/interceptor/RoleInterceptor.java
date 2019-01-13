package com.mycurdpro.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.Ret;
import com.mycurdpro.common.annotation.RequireRole;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 角色拦截器, 通过角色编码限制 用户操作
 *
 * @author zhangchuang
 */
public class RoleInterceptor implements Interceptor {

    private final static Logger LOG = LoggerFactory.getLogger(RoleInterceptor.class);

    @Override
    public void intercept(Invocation inv) {
        boolean flag = true;
        RequireRole requireRole = inv.getMethod().getAnnotation(RequireRole.class);
        String roleCodes = inv.getController().getSessionAttr(Constant.SYS_USER_ROLE_CODES);

        if (requireRole != null && roleCodes != null) {
            RequireRole.Relation relation = requireRole.relation();
            if (relation.equals(RequireRole.Relation.OR)) {
                flag = StringUtils.asListOrContains(roleCodes, requireRole.value());
            } else {
                flag = StringUtils.asListAndContains(roleCodes, requireRole.value());
            }
            LOG.debug("user roleCodes {}, annotation value: {}, annotation relation: {}  = {}"
                    , roleCodes, requireRole.value(), relation.name(), flag);
        }

        if (flag) {
            inv.invoke();
            return;
        }

        BaseController baseController = (BaseController) inv.getController();
        baseController.addServiceLog("RoleInterceptor: 访问无权限路径");
        // 如此 区分 ajax 并不完全准确, 例如 easyui form
        String requestType = baseController.getHeader("X-Requested-With");
        if (StringUtils.notEmpty(requestType)) {
            Ret ret = Ret.create().setFail().set("msg", "无权限操作！您的行为已被记录到日志。");
            inv.getController().renderJson(ret);
        } else {
            inv.getController().render("/WEB-INF/views/common/no_permission.ftl");
        }
    }
}
