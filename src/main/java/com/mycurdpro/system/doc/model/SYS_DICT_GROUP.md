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
| 8 | DEL_FLAG |  VARCHAR2(2) | ✘  |   |   | 删除标志，0未删除 1已删除  |


