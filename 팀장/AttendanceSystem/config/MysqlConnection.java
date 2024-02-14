package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {
    private static final String _driver = "com.mysql.cj.jdbc.Driver";
    private static final String _url = "jdbc:mysql://localhost:3306/attendancesystem";
    private static final String _username = "root";
    private static final String _password = "1234";
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            Class.forName(_driver);
            return DriverManager.getConnection(_url, _username, _password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("연결 끊김");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
