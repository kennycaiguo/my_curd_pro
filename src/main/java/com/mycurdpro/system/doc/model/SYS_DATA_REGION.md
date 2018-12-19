### SYS_DATA_REGION   地区数据 
| No.  | Field  | Type  | Nullable  | Key | Default | Remarks |
| :------------: | ------------ | ------------ | :------------: | ------------ | ------------ | ------------ |
| 1 | ID |  VARCHAR2(30) | ✘  | PRI  |   |   |
| 2 | NAME |  VARCHAR2(50) | ✔  |   |   | 名称  |
| 3 | PARENT_ID |  VARCHAR2(30) | ✔  |   |   | 父级  |
| 4 | SHORT_NAME |  VARCHAR2(50) | ✔  |   |   | 简称  |
| 5 | LEVEL_TYPE |  VARCHAR2(50) | ✔  |   |   | 级别  |
| 6 | CITY_CODE |  VARCHAR2(50) | ✔  |   |   | 城市代码  |
| 7 | ZIP_CODE |  VARCHAR2(50) | ✔  |   |   | 邮政编码  |
| 8 | MERGER_NAME |  VARCHAR2(150) | ✔  |   |   | 路径名  |
| 9 | LNG |  VARCHAR2(50) | ✔  |   |   | 精度  |
| 10 | LAT |  VARCHAR2(50) | ✔  |   |   | 纬度  |
| 11 | PINYIN |  VARCHAR2(50) | ✔  |   |   | 拼音  |
| 12 | HAS_DISABLE |  VARCHAR2(50) | ✔  |   |   | 是否禁用 0 未禁 1禁用  |


