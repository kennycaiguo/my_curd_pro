### SYS_DICT   码表，枚举值表 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键id  |
| 2 | GROUP_CODE |  VARCHAR2(50) | ✘  |   |   | 分类编码  |
| 3 | DICT_LABEL |  VARCHAR2(50) | ✘  |   |   | 文本  |
| 4 | DICT_VALUE |  VARCHAR2(50) | ✘  |   |   | 文本值  |
| 5 | CREATER |  VARCHAR2(30) | ✘  |   |   | 创建人  |
| 6 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 7 | UPDATER |  VARCHAR2(30) | ✔  |   |   | 最后修改人  |
| 8 | UPDATE_TIME |  DATE(7) | ✔  |   |   | 最后修改时间  |
| 9 | DICT_SORT |  NUMBER(22) | ✔  |   |   | 排序号  |


