package com.mycurdpro.system.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * Generated baseModel
 * DB table: SYS_USER_SETTING  用户设置项
 *
 * @author zhangchuang
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysUserSetting<M extends BaseSysUserSetting<M>> extends Model<M> implements IBean {
    // 主键id
    public String getId() {
        return getStr("ID");
    }

    public M setId
            (String id) {
        set("ID", id);
        return (M) this;
    }

    // 用户名
    public String getSysUser() {
        return getStr("SYS_USER");
    }

    public M setSysUser
            (String sysUser) {
        set("SYS_USER", sysUser);
        return (M) this;
    }

    // 主题
    public String getTheme() {
        return getStr("THEME");
    }

    public M setTheme
            (String theme) {
        set("THEME", theme);
        return (M) this;
    }

    // 主题颜色值
    public String getThemeColor() {
        return getStr("THEME_COLOR");
    }

    public M setThemeColor
            (String themeColor) {
        set("THEME_COLOR", themeColor);
        return (M) this;
    }
}
