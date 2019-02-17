package com.mycurdpro;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Duang;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.StrKit;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.LoginInterceptor;
import com.mycurdpro.common.interceptor.PermissionInterceptor;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.utils.guava.BaseCache;
import com.mycurdpro.common.utils.guava.CacheContainer;
import com.mycurdpro.common.utils.guava.LoginRetryLimitCache;
import com.mycurdpro.system.model.SysMenu;
import com.mycurdpro.system.model.SysUser;
import com.mycurdpro.system.model.SysUserRole;
import com.mycurdpro.system.model.SysUserSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 登录 controller
 */
@Clear({LoginInterceptor.class, PermissionInterceptor.class})
public class LoginController extends BaseController {

    private final static Logger LOG = LoggerFactory.getLogger(LoginController.class);
    private final static LoginService loginService = Duang.duang(LoginService.class);

    // 登录用户名密码cookie key
    private final static String usernameKey = "mycurdpro_username";
    private final static String passwordKey = "mycurdpro_password";

    /**
     * 登录页面
     */
    public void index() {
        String username = getCookie(usernameKey);
        String password = getCookie(passwordKey);
        LOG.debug("username from cookie: {}", username);
        LOG.debug("password from cookie:{}", password);
        // cookie username password 存在
        if (StringUtils.notEmpty(username) && StringUtils.notEmpty(password)) {
            SysUser sysUser = SysUser.dao.findByUsernameAndPassword(username, password);
            if (sysUser != null && "0".equals(sysUser.getUserState())) {
                sysUser.setLastLoginTime(new Date());
                sysUser.update();

                afterLogin(sysUser);

                addServiceLog("通过cookie登录");
                redirect("/dashboard");
                return;
            } else {
                // 清理 记住密码 cookie
                setCookie(usernameKey, null, 0);
                setCookie(passwordKey, null, 0);
            }
        }
        render("login.ftl");
    }

    /**
     * 登录表单提交地址
     */
    public void action() {
        String username = getPara("username");
        String password = getPara("password");

        /* username password 无效 */
        if (StrKit.isBlank(username)) {
            setAttr("errMsg", "请填写用户名。");
            render("login.ftl");
            return;
        }
        if (StrKit.isBlank(password)) {
            setAttr("errMsg", "请填写密码。");
            render("login.ftl");
            return;
        }
        SysUser sysUser = SysUser.dao.findByUsername(username);
        if (sysUser == null) {
            setAttr("errMsg", username + " 用户不存在。");
            render("login.ftl");
            return;
        }

        // 密码错误 n 次 锁定 m 分钟
        BaseCache<String, AtomicInteger> retryCache = CacheContainer.getLoginRetryLimitCache();
        AtomicInteger retryTimes = retryCache.getCache(username);
        if (retryTimes.get() >= LoginRetryLimitCache.RETRY_LIMIT) {
            setAttr("username", username);
            setAttr("errMsg", " 账号已被锁定, " + LoginRetryLimitCache.LOCK_TIME + "分钟后可自动解锁。 ");
            render("login.ftl");
            return;
        }
        password = HashKit.sha1(password);
        if (!sysUser.getPassword().equals(password)) {
            int nowRetryTimes = retryTimes.incrementAndGet();  // 错误次数 加 1
            setAttr("username", username);
            if ((LoginRetryLimitCache.RETRY_LIMIT - nowRetryTimes) == 0) {
                setAttr("errMsg", " 账号已被锁定, " + LoginRetryLimitCache.LOCK_TIME + "分钟后可自动解锁。 ");
            } else {
                setAttr("errMsg", " 密码错误, 再错误 "
                        + (LoginRetryLimitCache.RETRY_LIMIT - nowRetryTimes) + " 次账号将被锁定" + LoginRetryLimitCache.LOCK_TIME + "分钟。");
            }
            render("login.ftl");
            return;
        }
        retryCache.put(username, new AtomicInteger()); // 密码正确缓存数清0

        if (sysUser.getUserState().equals("1")) {
            setAttr("errMsg", username + " 用户被禁用，请联系管理员。");
            render("login.ftl");
            return;
        }

        /* username password 有效 */

        // 如果选中了记住密码且cookie信息不存在，生成新的cookie 信息
        String remember = getPara("remember");
        if ("on".equals(remember) && getCookie(usernameKey) == null) {
            setCookie(usernameKey, username, 60 * 60 * 24);  // 1天
            setCookie(passwordKey, password, 60 * 60 * 24);
        }

        sysUser.setLastLoginTime(new Date());
        sysUser.update();

        afterLogin(sysUser);

        // 登录日志
        addServiceLog("登录");
        redirect("/dashboard");
    }


    /**
     * 登录后将 用户相关信息放入到 session 中
     *
     * @param sysUser
     */
    private void afterLogin(SysUser sysUser) {
        // 登录用户信息
        setSessionAttr(Constant.SYS_USER, sysUser);
        // druid session 监控用
        setSessionAttr(Constant.SYS_USER_NAME, sysUser.getName());
        // 菜单
        String roleIds = SysUserRole.dao.findRoleIdsByUserId(sysUser.getId());
        LOG.debug("{} has role ids {}", sysUser.getUsername(), roleIds);
        List<SysMenu> sysMenus = loginService.findUserMenus(roleIds);
        setSessionAttr(Constant.SYS_USER_MENU, sysMenus);
        LOG.debug("{} has menu {}", sysUser.getUsername(), JSON.toJSONString(sysMenus));

        // 角色编码
        String rolecodes = SysUserRole.dao.findRoleCodesByUserId(sysUser.getId());
        setSessionAttr(Constant.SYS_USER_ROLE_CODES, rolecodes);

        // 用户配置
        SysUserSetting sysUserSetting = SysUserSetting.dao.findBySysUser(sysUser.getUsername());
        if(sysUserSetting==null){
          setSessionAttr(Constant.SYS_USER_THEME,"default");
        }else{
          setSessionAttr(Constant.SYS_USER_THEME,sysUserSetting.getTheme());
        }
    }


    /**
     * 退出
     */
    @ActionKey("/logout")
    public void logout() {
        addServiceLog("退出");
        // 移除session属性
        removeSessionAttr(Constant.SYS_USER);
        removeSessionAttr(Constant.SYS_USER_NAME);
        // 当前session 失效
        getSession().invalidate();
        // 清理 记住密码 cookie
        setCookie(usernameKey, null, 0);
        setCookie(passwordKey, null, 0);
        redirect("/login");
    }
}
