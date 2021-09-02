package company.com.DbHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public  static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
