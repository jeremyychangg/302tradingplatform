// 	******************************************************************************************
// 	**																						**
// 	**	Filename: JBDCConnection.java														**
// 	**																						**
// 	**	Description: Connects Java to MariaDB MySQL database via JBDC						**
// 	**																						**
// 	**																						**
// 	**	Contributors: Jeremy Chang															**
// 	**																						**
// 	**																						**
// 	**	Date Created: 15/05/2021															**
// 	**																						**
// 	**																						**
// 	**	Change Documentation																**
// 	**		> Initial Version																**
// 	**																						**
// 	**																						**
// 	**																						**
// 	******************************************************************************************

package tradingPlatform.database;

import tradingPlatform.Main;

import java.sql.DriverManager;

public class JBDCConnection {
    public JBDCConnection() {
        try {

            // specify the data source, username and password
            String url = "jdbc:mysql://localhost:3308/tradingPlatform";
            String username = "root";
            String password = "password.1";

            // get a connection
            Main.connection = DriverManager.getConnection(url, username, password);
            if (Main.connection == null){
                System.out.println("No connection!");
            } else {
                System.out.println("Connection!");
            }
        } catch (Exception sqle) {
            System.err.println(sqle);
        }
    }

}

