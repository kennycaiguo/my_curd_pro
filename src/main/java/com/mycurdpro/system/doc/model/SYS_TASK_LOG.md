### SYS_TASK_LOG   定时任务日志 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键id  |
| 2 | CLASS_NAME |  VARCHAR2(200) | ✘  |   |   | 定时任务类名  |
| 3 | START_TIME |  DATE(7) | ✘  |   |   | 开始时间  |
| 4 | END_TIME |  DATE(7) | ✘  |   |   | 结束时间  |
| 5 | RESULT |  VARCHAR2(20) | ✘  |   |   | 运行结果，success 或者 fail  |
| 6 | ERROR |  VARCHAR2(500) | ✔  |   |   | 异常信息  |


