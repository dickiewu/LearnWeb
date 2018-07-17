package name.wuxiaodong01.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class Connections {
    private  static  ComboPooledDataSource dataSource = new ComboPooledDataSource();
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();


    public static void startTransaction() throws SQLException {
        Optional<Connection> connectionOp = getConnection();
        if (connectionOp.isPresent()) {
            Connection connection = connectionOp.get();
            connection.setAutoCommit(false);
        }
    }

    public static void commit() throws SQLException {
        Optional<Connection> connectionOp = getConnection();
        if (connectionOp.isPresent()) {
            Connection connection = connectionOp.get();
            connection.commit();
        }
    }

    public static void rollbackQuietly(){
        Optional<Connection> connectionOp = getConnection();
        if (connectionOp.isPresent()) {
            Connection connection = connectionOp.get();
            try {
                connection.rollback();
            } catch (SQLException e) {

            }
        }
    }

    public static void closeQuietly(){
        Optional<Connection> connectionOp = getConnection();
        if (connectionOp.isPresent()) {
            Connection connection = connectionOp.get();
            DbUtils.closeQuietly(connection);
        }
    }

    public static Optional<Connection> getConnection() {
        Connection connection = connectionThreadLocal.get();
        if(connection == null){
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connectionThreadLocal.set(connection);
        }
        return Optional.ofNullable(connectionThreadLocal.get());
    }

    public static void remove(){
        connectionThreadLocal.remove();
    }
}
