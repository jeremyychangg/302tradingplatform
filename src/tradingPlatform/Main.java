package tradingPlatform;

import tradingPlatform.database.JBDCConnection;
import tradingPlatform.Marketplace;
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
//            Asset asset3 = new Asset( "Wireless Mouse", "Computer Accessories", 8.22);
//            BuyOrder order1 = new BuyOrder("EM00000018", "CA00000001",97.34, 2);
//            SellOrder order2 = new SellOrder("EM00000018", "CA00000001",97.34, 2);
//            Marketplace.ChangeOrderQuantity("BY00000001", 6);
            connection.close();

        } catch (Exception e){
            System.out.println("Main Error: " + e.getMessage());
            // Add Error handling
            // Apply overlay to text box GUI
        }


    }
}