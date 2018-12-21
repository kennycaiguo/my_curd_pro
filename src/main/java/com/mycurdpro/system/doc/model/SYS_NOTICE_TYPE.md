### SYS_NOTICE_TYPE   通知分类 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键id  |
| 2 | CATE |  VARCHAR2(50) | ✘  |   |   | 分类编码  |
| 3 | NAME |  VARCHAR2(50) | ✘  |   |   | 名称  |
| 4 | CODE |  VARCHAR2(20) | ✘  |   |   | 消息编码  |
| 5 | LOGO |  VARCHAR2(200) | ✘  |   |   | LOGO图标  |
| 6 | TEMPLATE |  VARCHAR2(500) | ✘  |   |   | 消息模板  |
| 7 | REMARK |  VARCHAR2(100) | ✘  |   |   | 备注  |
| 8 | UNTIL_EXPIRY_DAY |  NUMBER(9) | ✘  |   |   | 过期天数  |
| 9 | UNTIL_DEAD_DAY |  NUMBER(9) | ✘  |   |   | 存活天数  |
| 10 | CREATER |  VARCHAR2(30) | ✘  |   |   | 创建人  |
| 11 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 12 | UPDATER |  VARCHAR2(30) | ✔  |   |   | 最后修改人  |
| 13 | UPDATE_TIME |  DATE(7) | ✔  |   |   | 最后修改时间  |


