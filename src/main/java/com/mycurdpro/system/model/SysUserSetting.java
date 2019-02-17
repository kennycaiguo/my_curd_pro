package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.Page;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.system.model.base.BaseSysUserSetting;

/**
 * Generated model
 * DB: SYS_USER_SETTING  用户设置项
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysUserSetting extends BaseSysUserSetting<SysUserSetting> {
    public static final SysUserSetting dao = new SysUserSetting().dao();

    /**
     * 分页查询
     * @param pageNumber 第几页
     * @param pageSize   每页条数
     * @param where      查询条件
     * @return 分页数据
     */
    public Page<SysUserSetting>  page(int pageNumber,int pageSize,String where ){
        String sqlSelect = " select * ";
        String sqlExceptSelect = " from SYS_USER_SETTING  ";
        if (StringUtils.notEmpty(where)) {
            sqlExceptSelect += " where " + where;
        }
        return this.paginate(pageNumber,pageSize,sqlSelect,sqlExceptSelect);
    }

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    public SysUserSetting findBySysUser(String username){
        String sql = "select * from sys_user_setting where sys_user = ?  ";
        return  findFirst(sql,username);
    }

}
