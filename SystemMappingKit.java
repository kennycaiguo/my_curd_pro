package com.mycurdpro.system.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated MappingKit
 * system 模块数据表  MappingKit
 */
public class SystemMappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("SYS_DICT", "ID", SysDict.class);
		arp.addMapping("SYS_DICT_GROUP", "ID", SysDictGroup.class);
		arp.addMapping("SYS_MENU", "ID", SysMenu.class);
		arp.addMapping("SYS_ORG", "ID", SysOrg.class);
		arp.addMapping("SYS_ROLE", "ID", SysRole.class);
		arp.addMapping("SYS_ROLE_INCODE", "ID", SysRoleIncode.class);
		arp.addMapping("SYS_ROLE_MENU", "SYS_MENU_ID,SYS_ROLE_ID", SysRoleMenu.class);
		arp.addMapping("SYS_SERVICE_LOG", "ID", SysServiceLog.class);
		arp.addMapping("SYS_USER", "ID", SysUser.class);
		arp.addMapping("SYS_USER_ROLE", "SYS_ROLE_ID,SYS_USER_ID", SysUserRole.class);
		arp.addMapping("SYS_VISIT_LOG", "ID", SysVisitLog.class);
	}
}

