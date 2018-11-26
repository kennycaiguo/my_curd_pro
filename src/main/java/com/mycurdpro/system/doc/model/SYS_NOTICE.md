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


