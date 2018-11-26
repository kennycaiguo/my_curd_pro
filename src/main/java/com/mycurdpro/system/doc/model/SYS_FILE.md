### SYS_FILE   用户上传的文件 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   | 主键id  |
| 2 | CREATER |  VARCHAR2(30) | ✘  |   |   | 创建人  |
| 3 | CREATE_TIME |  DATE(7) | ✘  |   |   | 创建时间  |
| 4 | SAVE_ID |  VARCHAR2(30) | ✔  |   |   | 预留其它表主键  |
| 5 | PATH |  VARCHAR2(500) | ✘  |   |   | 文件存储地址  |
| 6 | ORI_NAME |  VARCHAR2(100) | ✘  |   |   | 文件原名  |
| 7 | MIME |  VARCHAR2(50) | ✔  |   |   | MIME类型  |
| 8 | TYPE |  VARCHAR2(20) | ✔  |   |   | 文件类型（文件后缀）  |
| 9 | SIZE |  NUMBER(22) | ✔  |   |   | 文件大小（单位B字节)  |
| 10 | REMARK |  VARCHAR2(500) | ✔  |   |   | 备注  |


