package tradingPlatform;

import tradingPlatform.database.JBDCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


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
        try {
            // write your code here
            new JBDCConnection();

            System.out.println("Test working");
//            Asset asset3 = new Asset( "Big Desk", "Furniture", 8.22);
            connection.close();

        } catch (Exception e){
            // Add Error handling
            // Apply overlay to text box GUI
        }


    }
}