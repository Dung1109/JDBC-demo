package utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {

    public Connection getConnection() {
        try {

            // 2. Load driver JDBC for sql server
            // for mysql
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // 3. Driver manager => Connection (url, user, password)
            String url = "jdbc:sqlserver://localhost:1433;databaseName=StudentMS;encrypt=true;trustServerCertificate=true";
            String user = "aw";
            String password = "1234";
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("connect successfully!");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
