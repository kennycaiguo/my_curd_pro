### SYS_SERVICE_LOG   业务日志，程序主动记录的重要操作日志 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键ID  |
| 2 | SYS_USER_IP |  VARCHAR2(30) | ✘  |   |   | 操作人Ip地址  |
| 3 | URL |  VARCHAR2(500) | ✘  |   |   | 访问路径  |
| 4 | CONTENT |  VARCHAR2(500) | ✘  |   |   | 操作内容  |
| 5 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 6 | SYS_USER |  VARCHAR2(30) | ✘  |   |   | 操作人  |


