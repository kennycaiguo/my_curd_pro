### SYS_MENU   系统菜单 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键ID  |
| 2 | NAME |  VARCHAR2(50) | ✘  |   |   | 菜单名称  |
| 3 | URL |  VARCHAR2(100) | ✘  |   |   | 菜单地址  |
| 4 | ICON |  VARCHAR2(100) | ✘  |   |   | 菜单图标  |
| 5 | SORT |  NUMBER(22) | ✔  |   |   | 排序号  |
| 6 | PID |  VARCHAR2(30) | ✘  |   |   | 父ID  |
| 7 | CREATER |  VARCHAR2(50) | ✘  |   |   | 创建人  |
| 8 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 9 | UPDATER |  VARCHAR2(50) | ✔  |   |   | 最后修改人  |
| 10 | EDIT_TIME |  DATE(7) | ✔  |   |   | 最后修改时间  |
| 11 | LEVEL |  NUMBER(22) | ✘  |   |   | 层级  |


