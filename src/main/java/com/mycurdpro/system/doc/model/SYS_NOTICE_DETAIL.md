### SYS_NOTICE_DETAIL   通知消息从表（发送人接收人） 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键id  |
| 2 | SYS_NOTICE_ID |  VARCHAR2(30) | ✘  |   |   | 通知id  |
| 3 | SENDER |  VARCHAR2(30) | ✔  |   |   | 消息发送人  |
| 4 | RECEIVER |  VARCHAR2(30) | ✘  |   |   | 消息接收人  |
| 5 | HAS_READ |  VARCHAR2(1) | ✘  |   |   | 使用阅读YN  |
| 6 | READ_TIME |  DATE(7) | ✔  |   |   | 阅读时间  |


