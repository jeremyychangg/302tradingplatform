package tradingPlatform;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static tradingPlatform.Main.connection;

public class Marketplace {
    public static void ChangeOrderPrice(String orderID, double newPrice) throws SQLException {
        Statement smt = connection.createStatement();
        String sqlchPrice
                = "UPDATE orders SET orderPrice = " + newPrice + " WHERE orderID = '" + orderID + "';";
        smt.executeUpdate(sqlchPrice);
    }

    public static void ChangeOrderQuantity(String orderID, int newQuantity) throws SQLException {
        Statement smt = connection.createStatement();
        String sqlchQuant
                = "UPDATE orders SET orderQuantity = " + newQuantity + " WHERE orderID = '" + orderID + "';";
        smt.executeUpdate(sqlchQuant);

    }


}
