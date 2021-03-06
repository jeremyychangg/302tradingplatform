// 	******************************************************************************************
// 	**																						**
// 	**	Filename: Marketplace.java  													    **
// 	**																						**
// 	**	Description:																		**
// 	**																						**
// 	**																						**
// 	**	Contributors: Jeremy Chang															**
// 	**																						**
// 	**																						**
// 	**	Date Created:																		**
// 	**																						**
// 	**																						**
// 	**	Change Documentation																**
// 	**		> Initial Version																**
// 	**																						**
// 	**																						**
// 	**																						**
// 	******************************************************************************************

package tradingPlatform;

import tradingPlatform.enumerators.OrderStatus;
import tradingPlatform.enumerators.OrderType;
import tradingPlatform.exceptions.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static tradingPlatform.Main.connection;

/**
 * Marketplace is used to execute general functions in the trading platform regarding orders
 */
public class Marketplace {
    /**
     * Used to change price of an outstanding order
     * @param orderID
     * @param newPrice
     * @throws SQLException
     * @throws InvalidOrderException
     * @throws NegativePriceException
     * @throws InvalidAssetException
     * @throws UnitException
     */
    public static void ChangeOrderPrice(String orderID, double newPrice)
            throws SQLException, InvalidOrderException, NegativePriceException, InvalidAssetException, UnitException {
        // Current order
        Order currentOrder = Order.findOrder(orderID);

        // Cannot edit completed order
        if (currentOrder.orderStatus == OrderStatus.COMPLETE) {
            throw new InvalidOrderException("Marketplace Error: Cannot edit completed order.");
        }

        // Change price in database
        Statement smt = connection.createStatement();
        String sqlchPrice
                = "UPDATE orders SET orderPrice = " + newPrice + " WHERE orderID = '" + orderID + "';";
        smt.executeUpdate(sqlchPrice);

        // Same order with updated information;
        currentOrder = Order.findOrder(orderID);

        // Get order type and attempt to execute to see if new price will execute
        if (currentOrder.orderType == OrderType.BUY) {
            BuyOrder newOrder = (BuyOrder) currentOrder;
            newOrder.ExecuteBuyOrder();
        } else if (currentOrder.orderType == OrderType.SELL) {
            SellOrder newOrder = (SellOrder) currentOrder;
            newOrder.ExecuteSellOrder();
        }
    }

    /**
     * Used to change quantity involved in an order
     * @param orderID
     * @param newQuantity
     * @throws SQLException
     * @throws InvalidOrderException
     * @throws NegativePriceException
     * @throws InvalidAssetException
     * @throws UnitException
     */
    public static void ChangeOrderQuantity(String orderID, int newQuantity)
            throws SQLException, InvalidOrderException, NegativePriceException, InvalidAssetException, UnitException, ChangeException {
        // Current order
        Order currentOrder = Order.findOrder(orderID);

        // Cannot edit completed order
        if (currentOrder.orderStatus == OrderStatus.COMPLETE) {
            throw new InvalidOrderException("Marketplace Error: Cannot edit completed order.");
        }

        if (newQuantity > currentOrder.quantFilled) {
            throw new ChangeException("Order quantity cannot be changed to less than filled amount");
        } else {
            currentOrder.orderQuant = newQuantity;
            currentOrder.quantRemain = newQuantity - currentOrder.quantFilled;
        }

        // Update in database
        Statement smt = connection.createStatement();
        String sqlchQuant
                = "UPDATE orders SET orderQuantity = " + currentOrder.orderQuant + ", quantRemain = " +
                currentOrder.quantRemain + "  WHERE orderID = '" + orderID + "';";
        smt.executeUpdate(sqlchQuant);

        // Same order with updated information;
        currentOrder = Order.findOrder(orderID);

        // Get order type and attempt to execute to see if quantity price will execute
        if (currentOrder.orderType == OrderType.BUY) {
            BuyOrder newOrder = (BuyOrder) currentOrder;
            newOrder.ExecuteBuyOrder();
        } else if (currentOrder.orderType == OrderType.SELL) {
            SellOrder newOrder = (SellOrder) currentOrder;
            newOrder.ExecuteSellOrder();
        }
    }

    /**
     * Executed from admin whenever they want to reconcile the system and pull up discrepancies in marketplace
     * @throws SQLException
     * @throws InvalidAssetException
     * @throws ReconciliationError
     */
    public static void RunReconciliation() throws SQLException, InvalidAssetException, ReconciliationError {
        ArrayList<String> incompleteOrders = new ArrayList<>();
        ArrayList<String> completeOrders = new ArrayList<>();

        // Check that no incomplete orders with quantRemain of 0
        Statement statement = connection.createStatement();
        String sqlIncOrders = "SELECT orderID FROM orders WHERE orderStatus = 'INCOMPLETE' and quantRemain = 0;";
        ResultSet getIncOrders = statement.executeQuery(sqlIncOrders);

        while (getIncOrders.next()) {
            incompleteOrders.add(getIncOrders.getString("orderID"));
        }

        // Set complete
        for (int i = 0; i < incompleteOrders.size(); i++) {
            String sqlUpdateStatus = "UPDATE orders SET orderStatus = ? WHERE orderID = ?;";
            PreparedStatement smt = connection.prepareStatement(sqlUpdateStatus);
            smt.setString(1, OrderStatus.COMPLETE.name());
            smt.setString(2, incompleteOrders.get(i));
            smt.executeUpdate();
        }

        // Check that no complete orders with quantRemain > 0
        String sqlComOrders = "SELECT orderID FROM orders WHERE orderStatus = 'COMPLETE' and quantRemain > 0;";
        ResultSet getComOrders = statement.executeQuery(sqlComOrders);

        while (getComOrders.next()) {
            completeOrders.add(getComOrders.getString("orderID"));
        }

        // Set incomplete
        for (int i = 0; i < completeOrders.size(); i++) {
            String sqlUpdateStatus = "UPDATE orders SET orderStatus = ? WHERE orderID = ?;";
            PreparedStatement smt = connection.prepareStatement(sqlUpdateStatus);
            smt.setString(1, OrderStatus.INCOMPLETE.name());
            smt.setString(2, completeOrders.get(i));
            smt.executeUpdate();
        }

        // Check inventory numbers
        // For each unit with inventory, loop through
        // Reconciliation checks that executed orders and quantities sum to the amount in the unit's inventory
            // Initial inventory will have no orderID, ordered inventory will have orderID

        // Get list of all unitIDs
        ArrayList<String> allUnits = new ArrayList<>();

        String sqlUnits = "SELECT unitID FROM units;";
        ResultSet getUnits = statement.executeQuery(sqlIncOrders);

        while (getUnits.next()) {
            allUnits.add(getUnits.getString("unitID"));
        }

        // Loop through units
        for (int i=0; i < allUnits.size(); i++) {
            Inventory inventory = new Inventory(allUnits.get(i));
            // Loop through each asset in inventory
            for (int x = 0; x < inventory.unitInventory.size(); x++) {
                String currentAsset = inventory.unitInventory.get(x).asset.GetAssetID();
                int expectedQuantity = inventory.unitInventory.get(x).quantity;
                int actualQuantity = 0;

                Statement smt = connection.createStatement();
                // Find initial quantity not from orders
                String sqlInitInv =
                        "SELECT * FROM inventory " +
                        "WHERE unitID = '" + allUnits.get(i) +"' and assetID = '" + currentAsset + "' and orderID IS NULL;";
                ResultSet getInitInv = smt.executeQuery(sqlInitInv);

                while (getInitInv.next()) {
                    actualQuantity += getInitInv.getInt("quantity");
                }

                // Find orders from this unit for this asset
                String sqlAssetOrders =
                        "SELECT orderType, quantFilled FROM orders " +
                        "WHERE unitID = '" + allUnits.get(i) +"' and assetID = '" + currentAsset + "';";
                ResultSet getOrders = smt.executeQuery(sqlAssetOrders);

                while (getOrders.next()) {
                    if (getOrders.getString("orderType") == "BUY") {
                        actualQuantity += getOrders.getInt("quantFilled");
                    } else if (getOrders.getString("orderType") == "SELL") {
                        actualQuantity -= getOrders.getInt("quantFilled");
                    }
                }

                if (expectedQuantity != actualQuantity) {
                    throw new ReconciliationError("Reconciliation Error: Asset - " + currentAsset +
                            "\nExpected Quant: " + expectedQuantity +
                            "\nActual Quant: " + actualQuantity);
                }

                // Otherwise this asset reconciles for this unit
            }
        }
    }
}
