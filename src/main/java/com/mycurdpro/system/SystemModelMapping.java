package com.mycurdpro.system;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.mycurdpro.system.model.*;

/**
 * Generated MappingKit
 * system 模块数据表  MappingKit
 *
 * @author zhangchuang
 */
public class SystemModelMapping {

    public static void mapping(ActiveRecordPlugin arp) {
        // 系统用户表
        arp.addMapping("SYS_USER", "ID", SysUser.class);
        // 用户角色中间表
        arp.addMapping("SYS_USER_ROLE", "SYS_USER_ID,SYS_ROLE_ID", SysUserRole.class);
        // 角色
        arp.addMapping("SYS_ROLE", "ID", SysRole.class);
        // 系统菜单
        arp.addMapping("SYS_MENU", "ID", SysMenu.class);
        // 角色菜单中间表
        arp.addMapping("SYS_ROLE_MENU", "SYS_ROLE_ID,SYS_MENU_ID", SysRoleMenu.class);
        // 组织机构表
        arp.addMapping("SYS_ORG", "ID", SysOrg.class);
        // 通知分类
        arp.addMapping("SYS_NOTICE_TYPE", "ID", SysNoticeType.class);
        // 系统通知类型角色中间表
        arp.addMapping("SYS_NOTICE_TYPE_SYS_ROLE", "SYS_NOTICE_TYPE_ID,SYS_ROLE_ID", SysNoticeTypeSysRole.class);
        // 通知类型系统用户中间表
        arp.addMapping("SYS_NOTICE_TYPE_SYS_USER", "SYS_NOTICE_TYPE_ID,SYS_USER_ID", SysNoticeTypeSysUser.class);
        // 通知消息
        arp.addMapping("SYS_NOTICE", "ID", SysNotice.class);
        // 通知消息从表（发送人接收人）
        arp.addMapping("SYS_NOTICE_DETAIL", "ID", SysNoticeDetail.class);
        // 码表，枚举值表
        arp.addMapping("SYS_DICT", "ID", SysDict.class);
        // 字典编码分组
        arp.addMapping("SYS_DICT_GROUP", "ID", SysDictGroup.class);
        // 用户上传的文件
        arp.addMapping("SYS_FILE", "ID", SysFile.class);
        // 业务日志，程序主动记录的重要操作日志
        arp.addMapping("SYS_SERVICE_LOG", "ID", SysServiceLog.class);
        // 系统访问日志
        arp.addMapping("SYS_VISIT_LOG", "ID", SysVisitLog.class);
        // 地区数据
        arp.addMapping("SYS_DATA_REGION", "ID", SysDataRegion.class);
        // 笔记分类
        arp.addMapping("SYS_NOTE_CATE", "ID", SysNoteCate.class);
        // 笔记
        arp.addMapping("SYS_NOTE", "ID", SysNote.class);
        // 定时任务日志
        arp.addMapping("SYS_TASK_LOG", "ID", SysTaskLog.class);
    }
}

