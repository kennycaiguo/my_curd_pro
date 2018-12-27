### SYS_NOTE    
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键  |
| 2 | TITLE |  VARCHAR2(100) | ✘  |   |   | 标题  |
| 3 | TYPE |  VARCHAR2(20) | ✘  |   |   | 类型 html 或 markdown  |
| 4 | CONTENT |  CLOB(4,000) | ✔  |   |   | 内容  |
| 5 | CATE_ID |  VARCHAR2(30) | ✔  |   |   | 分类ID  |
| 6 | STATE |  VARCHAR2(10) | ✔  |   |   | 状态  |
| 7 | USER_ID |  VARCHAR2(30) | ✘  |   |   | 用户id  |
| 8 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 9 | UPDATE_TIME |  DATE(7) | ✔  |   |   | 最后更新时间  |


