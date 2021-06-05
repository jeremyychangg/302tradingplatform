package tradingPlatform;

import tradingPlatform.database.JBDCConnection;
import tradingPlatform.Marketplace;
import tradingPlatform.exceptions.InvalidOrderException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Main {
    public static Connection connection;

    private static String currentUser;
    private static String currentUnit;

    public static String getCurrentUser(){
        return Main.currentUser;
    }
    public static String getCurrentUnit() { return  Main.currentUnit; }

    // Need methods in user and unit classes to set these variables on log in
    public static void setCurrentUser(String currentUser) {
        Main.currentUser = currentUser;
    }
    public static void setCurrentUnit(String currentUnit) {
        Main.currentUnit = currentUnit;
    }

    public static void main(String[] args) {
        try {
            // write your code here
            new JBDCConnection();


            System.out.println("Test working");


//            new Asset("Computer Mouse", "Computer Accessories");
//            new Asset("Table", "Furniture",100.8);

            // Temp set userID and unitID
            setCurrentUser("EM00000002");
            setCurrentUnit("IT00000045");

//            Asset asset3 = new Asset( "Wireless Mouse", "Computer Accessories", 8.22);
            BuyOrder order1 = new BuyOrder(getCurrentUser(), "CA00000001",97.34, 2);

            setCurrentUser("LD00000009");
            setCurrentUnit("FN00000061");

            SellOrder order2 = new SellOrder(getCurrentUser(), "CA00000001",105.34, 2);

            setCurrentUser("EM00000125");
            setCurrentUnit("IT00000005");

            SellOrder order3 = new SellOrder(getCurrentUser(), "CA00000001",65.34, 2);
//            Marketplace.ChangeOrderQuantity("BY00000001", 6);
            connection.close();

        } catch (Exception e){
            System.out.println("Main Error: " + e.getMessage());
            // Add Error handling
            // Apply overlay to text box GUI
        }


    }
}