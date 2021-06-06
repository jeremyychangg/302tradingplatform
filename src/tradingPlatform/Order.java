// 	******************************************************************************************
// 	**																						**
// 	**	Filename: Order.java			    												**
// 	**																						**
// 	**	Description: Orders are executed through this class									**
// 	**																						**
// 	**																						**
// 	**	Contributors: Jeremy Chang															**
// 	**																						**
// 	**																						**
// 	**	Date Created: 26/05/2021															**
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
import tradingPlatform.exceptions.ChangeException;
import tradingPlatform.exceptions.InvalidAssetException;
import tradingPlatform.exceptions.InvalidOrderException;
import tradingPlatform.exceptions.MultipleRowDeletionException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static tradingPlatform.Main.connection;

/**
 * Super class of orders with universal functionality across different orders
 */
public class Order {
    // Declare fields of order
    String orderID;
    String userID;
    String unitID;
    String assetID;
    LocalDateTime orderTime;
    OrderStatus orderStatus;
    OrderType orderType;
    double orderPrice;
    int orderQuant;
    int quantFilled;
    int quantRemain;

    /**
     * Creates an instance of any order that already exists. Purely for manipulation purposes.
     * @param orderID orderID
     * @param userID userID
     * @param unitID unitID
     * @param assetID assetID
     * @param orderTime Time of order
     * @param orderStatus Completed or Incomplete
     * @param orderType Buy or Sell
     * @param orderPrice Price of order
     * @param orderQuant Quantity of order
     * @param quantFilled Quantity of order filled
     * @param quantRemain Quantity of order remaining
     */
    public Order(String orderID, String userID, String unitID, String assetID, String orderTime, OrderStatus orderStatus,
            OrderType orderType, double orderPrice, int orderQuant, int quantFilled, int quantRemain) {
        this.orderID = orderID;
        this.userID = userID;
        this.unitID = unitID;
        this.assetID = assetID;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.orderTime = LocalDateTime.parse(orderTime, dtf);

        this.orderStatus = orderStatus;
        this.orderType = orderType;
        this.orderPrice = orderPrice;
        this.orderQuant = orderQuant;
        this.quantFilled = quantFilled;           // Initial quantity filled is 0
        this.quantRemain = quantRemain;
    }

    /**
     * Order instance that creates instance for new orders - setting some defaults. Some fields are implied.
     * @param userID
     * @param assetID
     * @param orderType
     * @param orderPrice
     * @param orderQuant
     * @throws SQLException
     */
    public Order(String userID, String assetID, OrderType orderType,
                 double orderPrice, int orderQuant) throws SQLException {
        this.userID = userID;
        this.assetID = assetID;
        this.orderStatus = OrderStatus.INCOMPLETE;      // Initialise new order as incomplete
        this.orderType = orderType;
        this.orderPrice = orderPrice;
        this.orderQuant = orderQuant;
        this.quantFilled = 0;           // Initial quantity filled is 0
        this.quantRemain = orderQuant;  // Quantity remaining is initial order quantity

        // Get unitID from main
        this.unitID = Main.getCurrentUnit();

        // Get current date time
        this.orderTime = LocalDateTime.now();
    }

    /**
     * Adds any order to the database
     * @throws SQLException
     * @throws InvalidAssetException
     */
    public void AddOrderDatabase() throws SQLException, InvalidAssetException {
        // Store order type
        String orderSubstring;
        if (orderType == OrderType.BUY) {
            orderSubstring = "BY";
        } else {
            orderSubstring = "SL";
        }

        // Check if asset exists
        try {
            if (!CheckAssetExists(this.assetID)) {
                throw new InvalidAssetException("Selected Asset is Invalid");
            }
        } catch (InvalidAssetException e) {
            System.out.println(e.getMessage());
        }

        // Get highest orderID from database
        int maxID = 0;
        Statement smt = connection.createStatement();
        String sqlMaxID
                = "SELECT max(substring(orderID, 3, 8)) as maxID FROM orders WHERE substring(orderID, 1, 2) = '"
                + orderSubstring + "';";
        ResultSet getMaxID = smt.executeQuery(sqlMaxID);

        // Extract string result and parse as integer
        if (getMaxID.next() && getMaxID.getString("maxID") != null) {
            maxID = Integer.parseInt(getMaxID.getString("maxID"));
        }

        String newID = orderSubstring + String.format("%08d", maxID + 1);
        this.orderID = newID;

        // Insert fields into database
        PreparedStatement newOrder =
                connection.prepareStatement(
                        "INSERT INTO orders VALUES (?,?,?,?,?,?,?,?,?,?,?);");

        newOrder.setString(1, this.orderID);
        newOrder.setString(2, this.userID);
        newOrder.setString(3, this.unitID);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        newOrder.setString(4, dtf.format(this.orderTime));

        newOrder.setString(5, this.assetID);
        newOrder.setString(6, this.orderStatus.name());
        newOrder.setString(7, this.orderType.name());
        newOrder.setDouble(8, this.orderPrice);
        newOrder.setInt(9, this.orderQuant);
        newOrder.setInt(10, this.quantFilled);
        newOrder.setInt(11, this.quantRemain);

        try {
            newOrder.execute();
        } catch (SQLException e) {

            // Handle if no database

            System.out.println("New Order Error: " + e.getMessage());
        }

    }

    /**
     * Checks if asset exists in database before performing an operation
     * @param assetID
     * @return True: Exists, False: Does not exist
     * @throws SQLException
     */
    public boolean CheckAssetExists(String assetID) throws SQLException {
        Statement smt = connection.createStatement();
        String sqlCheckAsset
                = "SELECT assetID FROM assets WHERE assetID = '" + assetID + "';";
        ResultSet assetIDs = smt.executeQuery(sqlCheckAsset);

        if (assetIDs.next() == false) {
            return false;
        } else  {
            return true;
        }
    }

    /**
     * Deletes an order from the database if user decides asset not needed anymore
     * @throws SQLException
     * @throws MultipleRowDeletionException
     */
    public void DeleteOrder() throws SQLException, MultipleRowDeletionException {
        Statement smt = connection.createStatement();
        String sqlDelOrder
                = "DELETE FROM orders WHERE orderID = '" + this.orderID + "';";
        int delNum = smt.executeUpdate(sqlDelOrder);

        // Throw error if more than one order was deleted
        if (delNum > 1) {
            throw new MultipleRowDeletionException("Warning: Multiple rows were deleted from this query");
        }
    }

    /**
     * Changes quantity of this order
     * @param quantity
     * @throws ChangeException
     * @throws SQLException
     */
    public void ChangeOrderQuantity(int quantity) throws ChangeException, SQLException {
        // Store old quantities
        int oldQuantRemain = this.quantRemain;
        int oldOrderQuant = this.orderQuant;

        // Check new quantity is not less than quantity already filled
        if (quantity > this.quantFilled) {
            throw new ChangeException("Order quantity cannot be changed to less than filled amount");
        } else {
            this.orderQuant = quantity;
            this.quantRemain = quantity - this.quantFilled;
        }

        // Update in database
        String sqlUpdateQuant = "UPDATE orders SET orderQuantity = ?, quantRemain = ? WHERE orderID = '" + this.orderID + "';";
        PreparedStatement smt = connection.prepareStatement(sqlUpdateQuant);
        smt.setInt(1, this.orderQuant);
        smt.setInt(2, this.quantRemain);
        smt.executeUpdate();
    }

    /**
     * Used to changed quantity remaining and filled in order, mainly when orders are executed
     * @param remaining New quantity remaining
     * @param filled New quantity filled
     * @throws InvalidOrderException
     * @throws SQLException
     */
    public void ChangeQuantRemainFilled(int remaining, int filled) throws InvalidOrderException, SQLException {
        // If quantity filled and quantity remaining does not sum to order quantity, throw exception
        if (remaining + filled != this.orderQuant) {
            throw new InvalidOrderException("Order executed resulted in a quantity discrepancy");
        }

        // Change quantities
        this.quantRemain = remaining;
        this.quantFilled = filled;

        // Set in database
        String sqlUpdateQuant = "UPDATE orders SET quantRemain = ?, quantFilled = ? WHERE orderID = '" + this.orderID + "';";
        PreparedStatement smt = connection.prepareStatement(sqlUpdateQuant);
        smt.setInt(1, this.quantRemain);
        smt.setInt(2, this.quantFilled);
        smt.executeUpdate();
    }

    /**
     * Changes the status of an order after execution
     * @param orderStatus
     * @throws SQLException
     */
    public void ChangeStatus(OrderStatus orderStatus) throws SQLException {
        // Only change if current status is different
        if (this.orderStatus != orderStatus) {
            this.orderStatus = orderStatus;

            // Change in database
            String sqlUpdateStatus = "UPDATE orders SET orderStatus = ? WHERE orderID = ?;";
            PreparedStatement smt = connection.prepareStatement(sqlUpdateStatus);
            smt.setString(1, this.orderStatus.name());
            smt.setString(2, this.orderID);
            smt.executeUpdate();
        }
    }

    /**
     * Find an instance of existing orders from a given order ID
     * @param orderID
     * @return Returns an instance of an order object
     * @throws SQLException
     * @throws InvalidOrderException
     */
    public static Order findOrder(String orderID) throws SQLException, InvalidOrderException {
        // Query orders table
        Statement smt = connection.createStatement();
        String findOrder = "SELECT * FROM orders WHERE orderID = '" + orderID + "';";
        ResultSet getOrder = smt.executeQuery(findOrder);

        // Return order if found
        if (getOrder.next() && getOrder.getString("orderID") != null) {
            return new Order(
                    getOrder.getString("orderID"),
                    getOrder.getString("userID"),
                    getOrder.getString("unitID"),
                    getOrder.getString("assetID"),
                    getOrder.getString("orderTime").substring(0,19),
                    OrderStatus.valueOf(getOrder.getString("orderStatus")),
                    OrderType.valueOf(getOrder.getString("orderType")),
                    getOrder.getDouble("orderPrice"),
                    getOrder.getInt("orderQuantity"),
                    getOrder.getInt("quantFilled"),
                    getOrder.getInt("quantRemain")
            );
        } else {
            throw new InvalidOrderException("Order Error: Order not found.");
        }
    }
}