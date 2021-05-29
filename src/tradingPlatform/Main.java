package tradingPlatform;

import tradingPlatform.database.JBDCConnection;
import tradingPlatform.exceptions.UserException;
import tradingPlatform.gui.employeeScreen;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    private static String currentUser;
    public static String getCurrentUser(){
        return Main.currentUser;
    }

    public static void resetCurrentUser(){
        setCurrentUser(null);
    }

    public static Connection connection;

    public static void setCurrentUser(String currentUser) {
        Main.currentUser = currentUser;
    }

    public static void main(String[] args) throws SQLException, UserException {
        try {
            new JBDCConnection();
        } catch (Exception e){
        }

//        new loginGUI();
        new employeeScreen();
//        new Screen();
    }
}

