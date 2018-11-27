package com.mycurdpro.common.config;

/**
 * 常量
 * @author zhangchuang
 */
public class Constant {
    // session 中存储的 SysUser 对象 的 KEY 名
    public final static String SYS_USER = "SYS_USER";

    // session 中存储的 SysUser.name 的 KEY名 (用于druid session 监控)
    public final static String SYS_USER_NAME = "SYS_USER_NAME";

    // 项目默认编码
    public final static String DEFAULT_ENCODEING = "UTF-8";

    // views 视图路径
    public final static String VIEW_PATH = "/WEB-INF/views/";

    // 操作提示信息
    public final static String ADD_SUCCESS = "添加成功";
    public final static String ADD_FAIL = "添加失败";
    public final static String DELETE_SUCCESS = "删除成功";
    public final static String DELETE_FAIL = "删除失败";
    public final static String UPDATE_SUCCESS = "修改成功";
    public final static String UPDATE_FAIL = "修改失败";
    public final static String AUDIT_OVER="审批完成";
    public final static String PARAM_IS_EMPTY="参数为空";
}
