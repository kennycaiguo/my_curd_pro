### SYS_USER   系统用户表 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键id  |
| 2 | USERNAME |  VARCHAR2(50) | ✘  |   |   | 用户民  |
| 3 | PASSWORD |  VARCHAR2(100) | ✘  |   |   | 密码  |
| 4 | NAME |  VARCHAR2(50) | ✘  |   |   | 姓名  |
| 5 | AVATAR |  VARCHAR2(200) | ✔  |   |   | 头像  |
| 6 | GENDER |  VARCHAR2(1) | ✘  |   |   | 性别M男F女  |
| 7 | ORG_ID |  VARCHAR2(30) | ✔  |   |   | 部门  |
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


