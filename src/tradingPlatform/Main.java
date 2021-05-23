package tradingPlatform;

import tradingPlatform.database.JBDCConnection;

import java.sql.Connection;


public class Main {
    private static String currentUser;
    public static String getCurrentUser(){
        return Main.currentUser;
    }


    public static Connection connection;

    public static void setCurrentUser(String currentUser) {
        Main.currentUser = currentUser;
    }

    public static void main(String[] args) {
        // write your code here
        new JBDCConnection();

    }
}