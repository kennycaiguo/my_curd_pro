### SYS_NOTE_CATE    
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键  |
| 2 | CATE_TITLE |  VARCHAR2(100) | ✘  |   |   | 分类名称  |
| 3 | SYS_USER_ID |  VARCHAR2(30) | ✘  |   |   | 用户id  |
| 4 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 5 | UPDATE_TIME |  DATE(7) | ✔  |   |   | 最后修改时间  |
| 6 | CATE_REMARK |  VARCHAR2(255) | ✔  |   |   | 备注  |
| 7 | PID |  VARCHAR2(30) | ✘  |   |   | 父级id  |


