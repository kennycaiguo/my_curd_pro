package com.mycurdpro;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.mycurdpro.common.utils.StringUtils;
import com.mycurdpro.system.model.SysMenu;

import java.util.*;

public class MainService {

    public  String  findRoleIdsByUserId(String userId){
        String sql = "select WM_CONCAT(c.ID) as ROLE_IDS" +
                "  from sys_user a, sys_user_role b,sys_role c " +
                "  where a.id = b.sys_user_id and b.sys_role_id = c.id  and a.id = ? ";
        Record record = Db.findFirst(sql,userId);
        if(record==null){
            return null;
        }
        return  record.getStr("role_IDS");
    }


    /**
     * 通过 用户完整的菜单
     * @param roleIds 多个role id，以逗号分隔
     * @return
     */
    public  List<SysMenu> findUserMenus(String roleIds) {
        if(StringUtils.isEmpty(roleIds)){
            return new ArrayList<>();
        }

        if(roleIds.contains(",") && !roleIds.contains("'")){
            roleIds = roleIds.replaceAll(",","','");
        }
        // 所有菜单
        List<SysMenu> allMenuList = SysMenu.dao.findAll();
        // 用户菜单
        List<SysMenu> userMenuList = SysMenu.dao.findByRoleIds(roleIds);
        // 完整的用户菜单
        Set<SysMenu> chainSet = new HashSet<SysMenu>();
        for (SysMenu menu : userMenuList) {
            chainSet.add(menu);
            getPChain(allMenuList, menu, chainSet);
        }
        //排序
        userMenuList = new ArrayList<>(chainSet);
        Collections.sort(userMenuList, (o1, o2) -> {
            if (o1.getSort() == null || o2.getSort() == null|| o1.getSort() < o2.getSort()) {
                return -1;
            }
            return 0;
        });
        return userMenuList;
    }

    /**
     * 获取 所有 父祖 菜单
     * @param allMenuList
     * @param menu
     * @param chainSet
     */
    private void getPChain(Collection<SysMenu> allMenuList, SysMenu menu, Set<SysMenu> chainSet) {
        for (SysMenu m : allMenuList) {
            if (menu.getPid() == m.getId()) {
                chainSet.add(m);
                getPChain(allMenuList, m, chainSet);
            }
        }
    }
}
