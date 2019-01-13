package com.mycurdpro.common.utils.gen.tools;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.generator.TypeMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * Oracle 元数据 工具
 *
 * @author zhangchuang
 */
public class OracleMetaUtils {
    private final static Logger LOG = LoggerFactory.getLogger(OracleMetaUtils.class);
    private final static DataSource dataSource = OracleDatasourceUtils.getDataSource();

    // （Jfinal 特色)
    private static final TypeMapping typeMapping = new TypeMapping();  // 数据类型 java类型映射

    /**
     * 获得 数据表信息
     *
     * @param schemaPattern oracle schema 名
     * @param tableNames    获取元数据的表名集合
     * @param includeColumn 是否包含列信息
     * @return 表元数据
     */
    public static List<TableMeta> loadTables(String schemaPattern, Set<String> tableNames, Boolean includeColumn) {
        Preconditions.checkNotNull(dataSource, " dataSource 不可为 null");

        List<TableMeta> tables = new ArrayList<>();
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            for (String tableName : tableNames) {
                tables.add(loadTable(conn, schemaPattern, tableName, includeColumn));
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
     * @param includeColumn 是否查询列信息
     * @return 表信息
     * @throws Exception 查询异常
     */
    private static TableMeta loadTable(Connection conn, String schemaPattern, String tableName, Boolean includeColumn) throws Exception {
        Preconditions.checkNotNull(conn, "代码生成器.md 获得 Connection对象 为 null ");

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
        if (includeColumn) {
            Map<String, String> nameJavaTypeMap = new HashMap<>();
            String sql = "select * from " + tableMeta.name + " where rownum < 1"; // oracle 特色
            Statement stm = conn.createStatement();
            ResultSet rs4 = stm.executeQuery(sql);
            ResultSetMetaData rsmd = rs4.getMetaData();

            // Jfinal 特色 （针对 oracle)
            for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
                String columnName = rsmd.getColumnName(i);
                int type = rsmd.getColumnType(i);
                String typeStr = null;
                if (type == Types.TINYINT) {
                    typeStr = "java.lang.Byte";
                } else if (type == Types.SMALLINT) {
                    typeStr = "java.lang.Short";
                }
                if (typeStr == null) {
                    String colClassName = rsmd.getColumnClassName(i);
                    typeStr = typeMapping.getType(colClassName);
                }
                if (typeStr == null) {
                    type = rsmd.getColumnType(i);
                    if (type == Types.BINARY || type == Types.VARBINARY || type == Types.LONGVARBINARY || type == Types.BLOB) {
                        typeStr = "byte[]";
                    } else if (type == Types.CLOB || type == Types.NCLOB) {
                        typeStr = "java.lang.String";
                    } else if (type == Types.TIMESTAMP || type == Types.DATE) {
                        typeStr = "java.util.Date";
                    } else {
                        typeStr = "java.lang.String";
                    }
                }
                if ("java.math.BigDecimal".equals(typeStr)) {
                    int scale = rsmd.getScale(i);            // 小数点右边的位数，值为 0 表示整数
                    int precision = rsmd.getPrecision(i);    // 最大精度
                    LOG.debug("typeStr {},scale {}, precision {}", typeStr, scale, precision);
                    if (scale == 0) {
                        if (precision <= 9) {
                            typeStr = "java.lang.Integer";
                        } else if (precision <= 18) {
                            typeStr = "java.lang.Long";
                        } else {
                            typeStr = "java.math.BigDecimal";
                        }
                    } else {
                        typeStr = "java.math.BigDecimal";
                    }
                }
                nameJavaTypeMap.put(columnName, typeStr);
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
