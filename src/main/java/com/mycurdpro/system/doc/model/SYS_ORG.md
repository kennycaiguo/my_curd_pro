### SYS_ORG   组织机构表 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键id  |
| 2 | NAME |  VARCHAR2(50) | ✘  |   |   | 名称  |
| 3 | ADDRESS |  VARCHAR2(100) | ✘  |   |   | 地址  |
| 4 | MARK |  VARCHAR2(200) | ✔  |   |   | 备注  |
| 5 | SORT |  NUMBER(9) | ✔  |   |   | 排序号  |
| 6 | CREATER |  VARCHAR2(50) | ✘  |   |   | 创建人  |
| 7 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 8 | UPDATER |  VARCHAR2(50) | ✔  |   |   | 最后修改人  |
| 9 | UPDATE_TIME |  DATE(7) | ✔  |   |   | 最后修改时间  |
| 10 | PID |  VARCHAR2(30) | ✘  |   |   | 父ID  |
| 11 | DIRECTOR |  VARCHAR2(50) | ✔  |   |   | 主管人  |
| 12 | CODE |  VARCHAR2(50) | ✘  |   |   | 编码  |


