package com.mycurdpro.common.activiti;

import com.jfinal.plugin.activerecord.DbKit;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ActivitiTransaction implements Transaction {

    protected Connection connection;
    protected DataSource dataSource;
    protected TransactionIsolationLevel level;
    protected boolean autoCommmit;

    public ActivitiTransaction(DataSource ds, TransactionIsolationLevel desiredLevel, boolean desiredAutoCommit) {
        dataSource = ds;
        level = desiredLevel;
        autoCommmit = desiredAutoCommit;
    }

    public ActivitiTransaction(Connection connection) {
        this.connection = connection;
    }

    /**
     * 重写了openConnection()方法，获取数据库连接是我业务打开的那个连接
     */

    @Override
    public Connection getConnection() throws SQLException {
        if (connection == null) {
            openConnection();
        }
        return connection;
    }

    /**
     * 连接关闭close()，提交commit()，回滚rollback() ，
     * 全部注释了。对数据库连接的操作都有我业务来控制，不让activiti控制了
     */

    @Override
    public void commit() throws SQLException {
    }

    @Override
    public void rollback() throws SQLException {
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            DbKit.getConfig().close(connection);
        }
    }

    protected void openConnection() throws SQLException {
        connection = DbKit.getConfig().getConnection();
        if (level != null) {
            connection.setTransactionIsolation(level.getLevel());
        }
    }
}