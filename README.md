#### **代码量少，简单美观。** :smirk:

#### 项目介绍

基于 jfinal oracle easyui 快速开发 后台脚手架。 

1. 权限管理（菜单级 按钮级）
2. 代码生成器 （单表、主从表， model、controller、页面，增删改查导入导出）
3. 前台多主题配色  （单个css全局替换配色 ）
4. 基于 websocket 系统通知


#### 系统环境
1. JDK8 及以上
2. oracle 11g
3. jfinal3.6 、freemarker、 easypoi3.2.0 、slf4j .....
4. easyui1.6.10 、 pure.css 、layer .....

#### 使用 教程
1. 已安装 jdk，maven，oracle 数据库。
2. 用管理员账号登录 oracle，新建 schema
```sql
 -- 创建账号 
 create user MYCURD identified by 111111;  
 -- 创建表空间 
 create tablespace MYCURD datafile 'd:\oracle11g\data\MYCURD.dbf' size 1024m;
 -- 用户分配表空间
 alert user MYCURD default tablespace MYCURD ;
 -- 用户授权
 grant create session,create table,unlimited tablespace to MYCURD;
``` 
3. 使用 IDE clone 本项目，等 maven 构建完成后，将 db目录下 MYCURD.sql 导入 数据库
2. 修改 resources 下 jdbc.properties 数据库连接信息，IDE 配置 server，运行项目。
3. 账号 admin, 密码 111111

#### 开发环境 推荐（class 热加载）
1. IDEA + JRebel + Tomcat     (前端页面开发量大 类应用)
2. Eclipse + jfinal-undertow  (数据接口类应用)

![地区数据](https://images.gitee.com/uploads/images/2019/0114/155356_0f52929d_608004.png "region.png")
![地区数据](https://images.gitee.com/uploads/images/2019/0114/155214_6caee02d_608004.png "pro.png")
![一对多](https://images.gitee.com/uploads/images/2019/0213/204941_4d2f3dc9_608004.png "1tm.png")
![个人笔记](https://images.gitee.com/uploads/images/2019/0114/155346_0d524b46_608004.png "note.png")


