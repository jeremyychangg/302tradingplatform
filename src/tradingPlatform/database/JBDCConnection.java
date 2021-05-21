package tradingPlatform.database;

import tradingPlatform.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JBDCConnection {
    public JBDCConnection() {
        try {

            // specify the data source, username and password
            String url = "jdbc:mysql://localhost:3308/myDB";
            String username = "root";
            String password = "password.1";

            // get a connection
            Main.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }

}
