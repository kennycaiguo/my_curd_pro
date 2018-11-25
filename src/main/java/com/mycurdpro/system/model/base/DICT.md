### SYS_USER   系统用户表 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键id  |
| 2 | USERNAME |  VARCHAR2(50) | ✘  |   |   | 用户民  |
| 3 | PASSWORD |  VARCHAR2(100) | ✘  |   |   | 密码  |
| 4 | NAME |  VARCHAR2(50) | ✘  |   |   | 姓名  |
| 5 | AVATAR |  VARCHAR2(200) | ✔  |   |   | 头像  |
| 6 | GENDER |  VARCHAR2(1) | ✘  |   |   | 性别M男F女  |
| 7 | ORG_ID |  VARCHAR2(30) | ✘  |   |   | 部门  |
| 8 | EMAIL |  VARCHAR2(100) | ✘  |   |   | 电子邮箱  |
| 9 | PHONE |  VARCHAR2(20) | ✘  |   |   | 电话  |
| 10 | JOB |  VARCHAR2(50) | ✘  |   |   | 职位  |
| 11 | JOB_LEVEL |  VARCHAR2(50) | ✘  |   |   | 职位级别  |
| 12 | DISABLE |  VARCHAR2(1) | ✔  |   |   | 是否禁用0未禁用1禁用  |
| 13 | WX |  VARCHAR2(255) | ✔  |   |   | 微信预留  |
| 14 | DD |  VARCHAR2(255) | ✔  |   |   | 钉钉预留  |
| 15 | LAST_LOGIN_TIME |  DATE(7) | ✔  |   |   | 最后登录时间  |
| 16 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 17 | CREATER |  VARCHAR2(50) | ✘  |   |   | 创建人  |
| 18 | UPDATE_TIME |  DATE(7) | ✔  |   |   | 最后修改时间  |
| 19 | UPDATER |  VARCHAR2(50) | ✔  |   |   | 最后修改人  |
------------
### SYS_USER_ROLE   用户角色中间表 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | SYS_USER_ID |  VARCHAR2(30) | ✘  | PRI  |   | 用户id  |
| 2 | SYS_ROLE_ID |  VARCHAR2(30) | ✘  | PRI  |   | 角色id  |
| 3 | CREATER |  VARCHAR2(50) | ✔  |   |   | 创建人  |
| 4 | CREATE_TIME |  DATE(7) | ✔  |   |   | 创建时间  |
------------
### SYS_ROLE   角色 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键id  |
| 2 | NAME |  VARCHAR2(50) | ✘  |   |   | 角色名称  |
| 3 | CODE |  VARCHAR2(50) | ✘  |   |   | 角色编码  |
| 4 | DESCRIPTION |  VARCHAR2(200) | ✔  |   |   | 角色描述  |
| 5 | SORT |  NUMBER(22) | ✔  |   |   | 排序号  |
| 6 | CREATER |  VARCHAR2(50) | ✘  |   |   | 创建人  |
| 7 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 8 | UPDATER |  VARCHAR2(50) | ✔  |   |   | 最后修改人  |
| 9 | UPDATE_TIME |  DATE(7) | ✔  |   |   | 最后修改时间  |
------------
### SYS_MENU   系统菜单 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键ID  |
| 2 | NAME |  VARCHAR2(50) | ✘  |   |   | 菜单名称  |
| 3 | URL |  VARCHAR2(100) | ✘  |   |   | 菜单地址  |
| 4 | ICON |  VARCHAR2(100) | ✘  |   |   | 菜单图标  |
| 5 | SORT |  NUMBER(22) | ✔  |   |   | 排序号  |
| 6 | PID |  VARCHAR2(30) | ✘  |   |   | 父ID  |
| 7 | CREATER |  VARCHAR2(50) | ✘  |   |   | 创建人  |
| 8 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 9 | UPDATER |  VARCHAR2(50) | ✔  |   |   | 最后修改人  |
| 10 | EDIT_TIME |  DATE(7) | ✔  |   |   | 最后修改时间  |
| 11 | LEVEL |  NUMBER(22) | ✘  |   |   | 层级  |
------------
### SYS_ROLE_MENU   角色菜单中间表 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | SYS_ROLE_ID |  VARCHAR2(30) | ✘  | PRI  |   | 角色id  |
| 2 | SYS_MENU_ID |  VARCHAR2(30) | ✘  | PRI  |   | 菜单id  |
| 3 | CREATER |  VARCHAR2(50) | ✔  |   |   | 创建人  |
| 4 | CREATE_TIME |  DATE(7) | ✔  |   |   | 创建时间  |
------------
### SYS_ROLE_INCODE   代码中硬编码的角色 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键id  |
| 2 | NAME |  VARCHAR2(50) | ✘  |   |   | 角色名称  |
| 3 | CODE |  VARCHAR2(50) | ✘  |   |   | 角色编码  |
| 4 | DESCRIPTION |  VARCHAR2(200) | ✔  |   |   | 角色描述  |
| 5 | CREATER |  VARCHAR2(50) | ✘  |   |   | 创建人  |
| 6 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 7 | UPDATER |  VARCHAR2(50) | ✔  |   |   | 最后修改人  |
| 8 | UPDATE_TIME |  DATE(7) | ✔  |   |   | 最后修改时间  |
------------
### SYS_USER_ROLEINCODE   用户角色中间表 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | SYS_USER_ID |  VARCHAR2(30) | ✘  | PRI  |   | 用户id  |
| 2 | SYS_ROLEINCODE_ID |  VARCHAR2(30) | ✘  | PRI  |   | 角色id  |
| 3 | CREATER |  VARCHAR2(50) | ✔  |   |   | 创建人  |
| 4 | CREATE_TIME |  DATE(7) | ✔  |   |   | 创建时间  |
------------
### SYS_ORG   组织机构表 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键id  |
| 2 | NAME |  VARCHAR2(50) | ✘  |   |   | 名称  |
| 3 | ADDRESS |  VARCHAR2(100) | ✘  |   |   | 地址  |
| 4 | MARK |  VARCHAR2(200) | ✔  |   |   | 备注  |
| 5 | SORT |  NUMBER(22) | ✔  |   |   | 排序号  |
| 6 | CREATER |  VARCHAR2(50) | ✘  |   |   | 创建人  |
| 7 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 8 | UPDATER |  VARCHAR2(50) | ✔  |   |   | 最后修改人  |
| 9 | UPDATE_TIME |  DATE(7) | ✔  |   |   | 最后修改时间  |
| 10 | PID |  VARCHAR2(30) | ✘  |   |   | 父ID  |
| 11 | DIRECTOR |  VARCHAR2(50) | ✔  |   |   | 主管人  |
| 12 | LEVEL |  NUMBER(22) | ✘  |   |   | 层级  |
------------
### SYS_NOTICE_TYPE   通知分类 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键id  |
| 2 | CATE |  VARCHAR2(50) | ✘  |   |   | 分类编码  |
| 3 | NAME |  VARCHAR2(50) | ✘  |   |   | 名称  |
| 4 | CODE |  VARCHAR2(50) | ✘  |   |   | 消息编码  |
| 5 | LOGO |  VARCHAR2(200) | ✘  |   |   | LOGO图标  |
| 6 | TEMPLATE |  VARCHAR2(500) | ✘  |   |   | 消息模板  |
| 7 | REMARK |  VARCHAR2(100) | ✔  |   |   | 备注  |
| 8 | UNTIL_EXPIRY_DAY |  NUMBER(22) | ✘  |   |   | 过期天数  |
| 9 | UNTIL_DEAD_DAY |  NUMBER(22) | ✘  |   |   | 存活天数  |
------------
### SYS_NOTICE_TYPE_SYS_ROLE   系统通知类型角色中间表 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | SYS_NOTICE_TYPE_ID |  VARCHAR2(30) | ✘  | PRI  |   | 通知类型id  |
| 2 | SYS_ROLE_ID |  VARCHAR2(30) | ✘  | PRI  |   | 角色id  |
| 3 | CREATER |  VARCHAR2(50) | ✘  |   |   | 创建人  |
| 4 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
------------
### SYS_NOTICE_TYPE_SYS_USER   通知类型系统用户中间表 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | SYS_NOTICE_TYPE_ID |  VARCHAR2(30) | ✘  | PRI  |   | 通知类型id  |
| 2 | SYS_USER_ID |  VARCHAR2(30) | ✘  | PRI  |   | 系统用户id  |
| 3 | CREATER |  VARCHAR2(255) | ✘  |   |   | 创建人  |
| 4 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
------------
### SYS_NOTICE   通知消息 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 系统主键  |
| 2 | TYPE_CODE |  VARCHAR2(50) | ✘  |   |   | 通知类型编码  |
| 3 | TITLE |  VARCHAR2(100) | ✘  |   |   | 标题  |
| 4 | CONTENT |  VARCHAR2(500) | ✘  |   |   | 内容  |
| 5 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 6 | EXPIRY_TIME |  DATE(7) | ✔  |   |   | 过期时间  |
| 7 | DEAD_TIME |  DATE(7) | ✔  |   |   | 必死时间  |
------------
### SYS_NOTICE_DETAIL   通知消息从表（发送人接收人） 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键id  |
| 2 | SYS_NOTICE_ID |  VARCHAR2(30) | ✘  |   |   | 通知id  |
| 3 | SENDER |  VARCHAR2(30) | ✔  |   |   | 消息发送人  |
| 4 | RECEIVER |  VARCHAR2(30) | ✘  |   |   | 消息接收人  |
| 5 | HAS_READ |  VARCHAR2(1) | ✘  |   |   | 使用阅读YN  |
| 6 | READ_TIME |  DATE(7) | ✔  |   |   | 阅读时间  |
------------
### SYS_DICT   码表，枚举值表 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键id  |
| 2 | GROUP_CODE |  VARCHAR2(50) | ✘  |   |   | 分类编码  |
| 3 | DICT_LABEL |  VARCHAR2(50) | ✘  |   |   | 文本  |
| 4 | DICT_VALUE |  VARCHAR2(50) | ✘  |   |   | 文本值  |
| 5 | CREATER |  VARCHAR2(30) | ✘  |   |   | 创建人  |
| 6 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 7 | UPDATER |  VARCHAR2(30) | ✔  |   |   | 最后修改人  |
| 8 | UPDATE_TIME |  DATE(7) | ✔  |   |   | 最后修改时间  |
| 9 | DICT_SORT |  NUMBER(22) | ✔  |   |   | 排序号  |
------------
### SYS_DICT_GROUP   字典编码分组 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键ID  |
| 2 | GROUP_NAME |  VARCHAR2(100) | ✘  |   |   | 分组名  |
| 3 | GROUP_CODE |  VARCHAR2(100) | ✘  |   |   | 分组编码  |
| 4 | CREATER |  VARCHAR2(50) | ✘  |   |   | 创建人  |
| 5 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 6 | UPDATER |  VARCHAR2(50) | ✔  |   |   | 最后修改人  |
| 7 | UPDATE_TIME |  DATE(7) | ✔  |   |   | 最后修改时间  |
------------
### SYS_FILE   用户上传的文件 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键id  |
| 2 | CREATER |  VARCHAR2(30) | ✘  |   |   | 创建人  |
| 3 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 4 | SAVE_ID |  VARCHAR2(30) | ✔  |   |   | 预留其它表主键  |
| 5 | PATH |  VARCHAR2(500) | ✘  |   |   | 文件存储地址  |
| 6 | ORI_NAME |  VARCHAR2(100) | ✘  |   |   | 文件原名  |
| 7 | MIME |  VARCHAR2(50) | ✔  |   |   | MIME类型  |
| 8 | TYPE |  VARCHAR2(20) | ✔  |   |   | 文件类型（文件后缀）  |
| 9 | SIZE |  NUMBER(22) | ✔  |   |   | 文件大小（单位B字节)  |
| 10 | REMARK |  VARCHAR2(500) | ✔  |   |   | 备注  |
------------
### SYS_SERVICE_LOG   业务日志，程序主动记录的重要操作日志 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键ID  |
| 2 | SYS_USER_IP |  VARCHAR2(30) | ✘  |   |   | 操作人Ip地址  |
| 3 | URL |  VARCHAR2(500) | ✘  |   |   | 访问路径  |
| 4 | CONTENT |  VARCHAR2(500) | ✘  |   |   | 操作内容  |
| 5 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 6 | SYS_USER |  VARCHAR2(30) | ✘  |   |   | 操作人  |
------------
### SYS_VISIT_LOG   系统访问日志 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键id  |
| 2 | SYS_USER |  VARCHAR2(30) | ✘  |   |   | 操作人  |
| 3 | SYS_USER_IP |  VARCHAR2(30) | ✘  |   |   | 操作人ip  |
| 4 | URL |  VARCHAR2(500) | ✘  |   |   | 访问地址  |
| 5 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 6 | TYPE |  VARCHAR2(20) | ✘  |   |   | 访问类型  |
| 7 | PARAM |  VARCHAR2(255) | ✔  |   |   | 参数  |


