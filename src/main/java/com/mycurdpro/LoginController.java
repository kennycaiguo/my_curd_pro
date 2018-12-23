package com.mycurdpro;

import com.google.common.base.Objects;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.mycurdpro.common.base.BaseController;
import com.mycurdpro.common.config.Constant;
import com.mycurdpro.common.interceptor.LoginInterceptor;
import com.mycurdpro.common.interceptor.PermissionInterceptor;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.common.utils.guava.BaseCache;
import com.mycurdpro.common.utils.guava.CacheContainer;
import com.mycurdpro.system.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 登录 controller
 */
@Clear({LoginInterceptor.class, PermissionInterceptor.class})
public class LoginController extends BaseController {

    private final static Logger LOG = LoggerFactory.getLogger(LoginController.class);
    // 输错密码 锁定用户
    private final static int RETRY_TIMES = PropKit.use("config.properties").getInt("loginRetryLimitTime");
    private final static int LOCK_TIME_M = PropKit.use("config.properties").getInt("lockTime");
    // 登录用户名密码cookie key
    private final static String usernameKey = PropKit.use("config.properties").get("cookie_username_key");
    private final static String passwordKey = PropKit.use("config.properties").get("cookie_password_key");

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
            if (sysUser!=null && "0".equals(sysUser.getUserState())) {
                sysUser.setLastLoginTime(new Date());
                sysUser.update();
                // session 中放入登录用户信息 TODO 更好的方案
                setSessionAttr(Constant.SYS_USER, sysUser);
                setSessionAttr(Constant.SYS_USER_NAME, sysUser.getName());

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
        if (retryTimes.get() >= RETRY_TIMES) {
            setAttr("username", username);
            setAttr("errMsg", " 账号已被锁定, " + LOCK_TIME_M + "分钟后可自动解锁。 ");
            render("login.ftl");
            return;
        }
        password = HashKit.sha1(password);
        if (!sysUser.getPassword().equals(password)) {
            int nowRetryTimes = retryTimes.incrementAndGet();  // 错误次数 加 1
            setAttr("username", username);
            if ((RETRY_TIMES - nowRetryTimes) == 0) {
                setAttr("errMsg", " 账号已被锁定, " + LOCK_TIME_M + "分钟后可自动解锁。 ");
            } else {
                setAttr("errMsg", " 密码错误, 再错误 "
                        + (RETRY_TIMES - nowRetryTimes) + " 次账号将被锁定" + LOCK_TIME_M + "分钟。");
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
            setCookie(usernameKey, username, 60 * 60 * 24 * 1);  // 1天
            setCookie(passwordKey, password, 60 * 60 * 24 * 1);
        }

        sysUser.setLastLoginTime(new Date());
        sysUser.update();

        // 登录用户信息
        setSessionAttr(Constant.SYS_USER, sysUser);
        // druid session 监控用
        setSessionAttr(Constant.SYS_USER_NAME, sysUser.getName());

        // 登录日志
        addServiceLog("登录");
        redirect("/dashboard");
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
