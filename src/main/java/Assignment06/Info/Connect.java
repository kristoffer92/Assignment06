package Assignment06.Info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static final String url;
    private static final String user;
    private static final String password;

    static{
        url="jdbc:mysql://localhost:3306/world?&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        user="root";
        password="1234";
    }

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url,user,password);
    }
}
