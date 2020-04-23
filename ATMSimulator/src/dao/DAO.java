package dao;

import com.mysql.cj.MysqlConnection;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public interface DAO {
    @Deprecated
    // 查找并返回数据源对象
    static DataSource getDataSource() {
        DataSource ds = null;
        Context ctxt;
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        props.setProperty("java.naming.provider.url", "localhost:1099");
        try {
//            ctxt = new InitialContext();
            ctxt = new InitialContext(props);
            ds = (DataSource)ctxt.lookup("java:comp/env/jdbc/dataSourceStorageLocal");
        } catch (NamingException e) {
            e.printStackTrace();
            System.out.println("DAO异常:" + e);
        }
        return ds;
    }

    @Deprecated
    // 返回连接对象方法
    default Connection getConn() throws DAOException {
        DataSource ds = getDataSource();
        Connection conn = null;
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("连接异常:" + e);
        }
        return conn;
    }

    default Connection getConnection() {
        String url = "jdbc:mysql://127.0.0.1:3306/bank?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "stocker";
        try {
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setUrl(url);
            mysqlDataSource.setUser(user);
            mysqlDataSource.setPassword(password);
            return mysqlDataSource.getConnection();
//            Class.forName("com.mysql.jdbc.Driver");
//            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
