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


