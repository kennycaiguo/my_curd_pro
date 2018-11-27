### Oracle 数据库  number(x,y) , 代码生成器规则。
 1. y=0  & x<=9 生成 java.lang.Integer
 2. y=0 & 9<x<=19 生成 java.lang.Long
 3. y=0 & x>19 生成 java.math.BigDecimal
 4. y!=0 生成 java.math.BigDecimal