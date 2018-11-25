package com.mycurdpro.common.utils.codegenerator;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.generator.TypeMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Oracle 元数据 工具
 *
 * @author zhangchuang
 */
public class OracleMetaUtils {
    private final static Logger LOG = LoggerFactory.getLogger(OracleMetaUtils.class);


    static DataSource dataSource;                      //  数据源
    // （Jfinal 特色)
    private static final TypeMapping typeMapping = new TypeMapping();// 数据类型 java类型映射


    /**
     * 获得 数据表信息
     *
     * @param schemaPattern oracle schema 名
     * @param tableNames    获取元数据的表名集合
     * @param hasColumn     是否包含列信息
     * @return 表元数据
     */
    public static List<TableMeta> loadTables(String schemaPattern, List<String> tableNames, Boolean hasColumn) {
        Preconditions.checkNotNull(dataSource, " dataSource 不可为 null");

        List<TableMeta> tables = new ArrayList<>();
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            for (String tableName : tableNames) {
                tables.add(loadTable(conn, schemaPattern, tableName, hasColumn));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return tables;
    }


    /**
     * 查询表结构数据
     *
     * @param conn          数据库连接
     * @param schemaPattern oracle schema 名
     * @param tableName     表名
     * @param hasColumn     是否查询列信息
     * @return 表信息
     * @throws Exception 查询异常
     */
    private static TableMeta loadTable(Connection conn, String schemaPattern, String tableName, Boolean hasColumn) throws Exception {
        Preconditions.checkNotNull(conn, "代码生成器 获得 Connection对象 为 null ");

        DatabaseMetaData dbMeta = conn.getMetaData();

        // 查询表信息
        ResultSet rs = dbMeta.getTables(conn.getCatalog(), schemaPattern, tableName, null);
        TableMeta tableMeta = new TableMeta();
        rs.next();
        tableMeta.name = rs.getString("TABLE_NAME");
        tableMeta.remark = rs.getString("REMARKS");
        tableMeta.nameCamel = StrKit.toCamelCase(tableMeta.name.toLowerCase());
        tableMeta.nameCamelFirstUp = StrKit.firstCharToUpperCase(tableMeta.nameCamel);
        ResultSet pkrs = dbMeta.getPrimaryKeys(conn.getCatalog(), null, tableMeta.name);
        List<String> tablePrimaryKeys = new ArrayList<>();
        while (pkrs.next()) {
            tablePrimaryKeys.add(pkrs.getString("COLUMN_NAME"));
        }
        tableMeta.primaryKeys = tablePrimaryKeys;

        // 查询列信息
        if (hasColumn) {
            Map<String, String> nameJavaTypeMap = new HashMap<>();
            String sql = "select * from " + tableMeta.name + " where rownum < 1"; // oracle 特色
            Statement stm = conn.createStatement();
            ResultSet rs4 = stm.executeQuery(sql);
            ResultSetMetaData rsmd = rs4.getMetaData();

            // Jfinal 特色
            for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
                String columnName = rsmd.getColumnName(i);
                String colClassName = rsmd.getColumnClassName(i);        // java sql 类型
                String typeStr = typeMapping.getType(colClassName); // 适合jfinal 的 java 类型
                if (typeStr != null) {
                    nameJavaTypeMap.put(columnName, typeStr);
                } else {
                    int type = rsmd.getColumnType(i);
                    if (type != -2 && type != -3 && type != 2004) {
                        if (type != 2005 && type != 2011) {
                            nameJavaTypeMap.put(columnName, "java.lang.String");
                        } else {
                            nameJavaTypeMap.put(columnName, "java.lang.String");
                        }
                    } else {
                        nameJavaTypeMap.put(columnName, "byte[]");
                    }
                }
            }

            List<ColumnMeta> columnMetas = new ArrayList<>();
            rs = dbMeta.getColumns(conn.getCatalog(), schemaPattern, tableMeta.name, null);
            while (rs.next()) {
                ColumnMeta columnMeta = new ColumnMeta();

                columnMeta.name = rs.getString("COLUMN_NAME");
                columnMeta.remark = rs.getString("REMARKS");
                columnMeta.dbType = rs.getString("TYPE_NAME");

                columnMeta.defaultValue = rs.getString("COLUMN_DEF");

                columnMeta.isNullable = "YES".equals(rs.getString("IS_NULLABLE"));
                columnMeta.size = rs.getInt("COLUMN_SIZE");
                columnMeta.decimalDigits = rs.getInt("DECIMAL_DIGITS");

                columnMeta.nameCamel = StrKit.toCamelCase(columnMeta.name.toLowerCase());
                columnMeta.nameCamelFirstUp = StrKit.firstCharToUpperCase(columnMeta.nameCamel);
                columnMeta.setJavaType(nameJavaTypeMap.get(columnMeta.name));
                columnMeta.isPrimaryKey = tableMeta.primaryKeys.contains(columnMeta.name);

                columnMetas.add(columnMeta);
            }
            tableMeta.setColumnMetas(columnMetas);
        }

        LOG.debug(JSON.toJSONString(tableMeta));
        return tableMeta;
    }

}
