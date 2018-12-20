package com.mycurdpro.system.service;


import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * 用户工具
 */
public class SysUserService {


    /**
     * 用户是否拥有某些角色
     * @param userId     用户id
     * @param roleCodes  角色编码数组
     * @return true 有，false 没有
     */
    public static boolean checkRoles(String userId,String[] roleCodes){
        boolean flag = true;
        String sql = "select count(1) as c from sys_user_role a,sys_role b where a.sys_role_id = b.id and a.sys_user_id = ? and b.code = ?";
        Record record;
        for(String roleCode:roleCodes){
            record = Db.findFirst(sql,userId,roleCode);
            if(record.getLong("c")==0){
                flag = false;
                break;
            }
        }
        return flag;
    }
}
