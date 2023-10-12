package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class ConnectionConfig {

    public static Connection connection() throws ClassNotFoundException, SQLException {
        Locale.setDefault(Locale.ENGLISH);

        String url="jdbc:mysql://localhost:3306/library_data";
        String username="root";
        String pass="1234";

        Class.forName("com.mysql.jdbc.Driver");

        return DriverManager.getConnection(url,username,pass);

    }
}
