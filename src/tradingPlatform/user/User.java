// 	******************************************************************************************
// 	**																						**
// 	**	Filename: UserException.java														**
// 	**																						**
// 	**	Description: User Exception class for User											**
// 	**																						**
// 	**																						**
// 	**	Contributors: Natalie Smith										                    **
// 	**																						**
// 	**																						**
// 	**	Date Created: 															            **
// 	**																						**
// 	**																						**
// 	**	Change Documentation																**
// 	**																						**
// 	**																						**
// 	******************************************************************************************
package tradingPlatform.user;

import tradingPlatform.exceptions.UnitException;
import tradingPlatform.exceptions.UserException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static tradingPlatform.Main.*;

/**
 * This class is used to
 * @author Natalie Smith
 */
public class User {
    private String userID;
    private String firstName;
    private String lastName;
    private String unitID;
    private String password;
    private UserType accountType = UserType.Employee;

    public String returnUserID() {
        return this.userID;
    }

    public String returnfirstName() {
        return this.firstName;
    }

    public String returnlastName() {
        return this.lastName;
    }

    public String returnunitID() {
        return this.unitID;
    }

    public UserType returnAccountType() {
        return this.accountType;
    }

    public String returnpassword() {
        return this.password;
    }


    public User(String firstName, String lastName, String unitID, UserType accountType) throws UserException, SQLException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.unitID = unitID;
        this.accountType = accountType;
    }

    public User(String userID) throws SQLException {
        setCurrentUser(userID);
        if (usernameExists(userID)) {
            this.userID = userID;
            this.firstName = getFirstName();
            this.lastName = getLastName();
            this.accountType = getAccountType();
            this.unitID = getUnitID();
        }
    }

    public User(String firstName, String lastName, String unitID, String password, UserType accountType) throws Exception {
        this.firstName = firstName;
        this.lastName = lastName;
        this.unitID = unitID;
        this.password = password;
        this.accountType = accountType;

        // Throw exceptions if requirements not met
        if (firstName == null || firstName == "") {
            throw new UserException("First Name cannot be null or empty.");
        }
        if (lastName == null || lastName == "") {
            throw new UserException("Last Name cannot be null or empty.");
        }
        if (unitID == null || unitID == "") {
            throw new UserException("Unit ID cannot be null or empty.");
        }
        if (!unitExists(unitID)) {
            throw new UserException("Unit ID doesn't exist. Enter in valid unitID.");
        }
        if (password == null || password == "") {
            throw new UserException("Password cannot be null or empty.");
        }
        if (accountType == null) {
            throw new UserException("Account Type cannot be null or empty.");
        }
        if (userTypeToS(accountType) == null) {
            throw new UserException("Account Type not a valid Account Type.");
        }

        // Based on the input for the account, set the userID initial accordingly
        String accType = "";
        String intialID = "";
        switch (accountType) {
            case Employee:
                intialID = "S";
                accType = userTypeToS(accountType);
                break;
            case Admin:
                intialID = "A";
                accType = userTypeToS(accountType);
                break;
            case Lead:
                intialID = "L";
                accType = userTypeToS(accountType);
                break;
            default:
                throw new UserException("Not valid UserType");
        }

        Statement statement = connection.createStatement();

        int maxUserID = 0;
        String sqlMaxUserID
                = "SELECT max(substring(userID, 2, 5)) as maxUserID FROM users WHERE substring(userID, 1, 1) = '"
                + intialID + "';";
        ResultSet getMaxID = statement.executeQuery(sqlMaxUserID);

        if (getMaxID.next() && getMaxID.getString("maxUserID") != null) {
            maxUserID = Integer.parseInt(getMaxID.getString("maxUserID"));
        }

        String newUserID = intialID + String.format("%04d", maxUserID + 1);
        System.out.println(newUserID);
        this.userID = newUserID;
//
//        PreparedStatement newUser = connection.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?,?);");
//        newUser.clearParameters();
//        newUser.setString(1, newUserID);
//        newUser.setString(2, firstName);
//        newUser.setString(3, lastName);
//        newUser.setString(4, unitID);
//        newUser.setString(5, accType);
//        newUser.setString(6, password);
//
//        newUser.execute();
    }

    public void addUserToDatabase(User user) throws Exception, UnitException {
    }

    /**
     * This function is used to get the account type of the user
     *
     * @return
     * @author Natalie Smith
     */
    public static UserType getAccountType() throws SQLException {
        // Search the database for the type of account
        String accountString = "";

        // Retrieve the account type string through connection and result set
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT accountType FROM users WHERE userID = '"
                + getCurrentUser() + "';");
        if (rs.next() && rs.getString("accountType") != null) {
            accountString = rs.getString("accountType");
        }

        // Return the relative UserType enum value if found, or else return null
        UserType outputType;
        for (UserType u : UserType.values()) {
            if (u.name().equals(accountString)) {
                return u;
            }
        }
        return null;
    }


    /**
     * Used to convert and return string version of a accountInput type
     *
     * @param accInput
     * @return
     */
    public String userTypeToS(UserType accInput) {
        for (UserType u : UserType.values()) {
            if (u.equals(accInput)) {
                return u.name();
            }
        }
        return null;
    }


    /**
     * The getCredits function is used to retrieve the credits of the user
     *
     * @return
     */
    public static float getCredits() throws SQLException {
        // search the Units database and return the credit of the given unit
        float credits = 0.0F;
        Statement statement = connection.createStatement();
        String queryCredits = "SELECT units.creditBalance " +
                "FROM units LEFT JOIN users " +
                "ON units.unitID = users.unitID " +
                "WHERE users.userID = '" + getCurrentUser() + "';";
        ResultSet creditsBalance = statement.executeQuery(queryCredits);
        if (creditsBalance.next() && creditsBalance.getString("creditBalance") != null) {
            credits = Float.parseFloat(creditsBalance.getString("creditBalance"));
        }
        // Extract the integer value of credits
        return credits;
    }


    /**
     * The getName function returns the first name of the user
     *
     * @return
     */
    public static String getFirstName() throws SQLException {
        String firstName = "";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT firstName FROM users WHERE userID = '" + getCurrentUser() + "';");
        if (rs.next() && rs.getString("firstName") != null) {
            firstName = rs.getString("firstName");
        }
        return firstName;
    }


    /**
     * Get the last name of the user
     *
     * @return
     * @throws SQLException
     */
    public static String getLastName() throws SQLException {
        String lastName = "";
        Statement statement = connection.createStatement();
        String queryLast = "SELECT lastName " +
                "FROM users WHERE userID = '" + getCurrentUser() + "'";
        ResultSet lastNameQuery = statement.executeQuery(queryLast);
        if (lastNameQuery.next() && lastNameQuery.getString("lastName") != null) {
            lastName = lastNameQuery.getString("lastName");
        }
        return lastName;
    }


    /**
     * The method is used to determine if the user inputted actually exists within the database
     *
     * @param findUserID
     * @return
     */
    public static boolean usernameExists(String findUserID) throws SQLException {
        try {
            String exists = null;
            Statement statement = connection.createStatement();
            String existUserQuery = "SELECT userID FROM users WHERE userID = '" + findUserID + "';";
            ResultSet userIDFind = statement.executeQuery(existUserQuery);
            if (userIDFind.next() && userIDFind.getString("userID") != null) {
                exists = userIDFind.getString("userID");
            }
            if (exists.equals(findUserID)) {
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            throw new SQLException("The inputted username does not currently exist.");
        }
    }


    /**
     * Function used to find if a unit exists
     *
     * @param findUnitID
     * @return
     * @throws SQLException
     */
    public static boolean unitExists(String findUnitID) throws Exception {
        try {
            String exists = null;
            Statement statement = connection.createStatement();
            String existUserQuery = "SELECT unitID FROM units WHERE unitID = '" + findUnitID + "';";
            ResultSet rs = statement.executeQuery(existUserQuery);
            if (rs.next() && rs.getString("unitID") != null) {
                exists = rs.getString("unitID");
            }
            if (exists.equals(findUnitID)) {
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            throw new SQLException("UnitID does not currently exist.");
        }
    }


    public static String getUnitID() throws SQLException {
        String exists = null;
        Statement statement = connection.createStatement();
        String existUserQuery = "SELECT unitID FROM users WHERE userID = '" + getCurrentUser() + "';";
        ResultSet userIDFind = statement.executeQuery(existUserQuery);
        if (userIDFind.next() && userIDFind.getString("unitID") != null) {
            exists = userIDFind.getString("unitID");
        }
        return exists;
    }


    public String getUnitID(String userID) throws SQLException {
        String exists = null;
        Statement statement = connection.createStatement();
        String existUserQuery = "SELECT unitID FROM users WHERE userID = '" + userID + "';";
        ResultSet userIDFind = statement.executeQuery(existUserQuery);
        if (userIDFind.next() && userIDFind.getString("unitID") != null) {
            exists = userIDFind.getString("unitID");
        }
        return exists;
    }


    /**
     * @param passMod
     */
    public void changePassword(String passMod) throws SQLException {
        // determine if the value is a valid password
        // input the SQL query for the database
        String passwordInputQuery = "UPDATE users SET password = ? WHERE assetID = '?';";
        PreparedStatement updatePassword = connection.prepareStatement(passwordInputQuery);
        updatePassword.clearParameters();
        updatePassword.setString(1, passMod);
        updatePassword.setString(2, this.userID);
        updatePassword.executeUpdate();
    }


    /**
     * Method is used to set the type of account for the user
     *
     * @param inputType
     */
    public static boolean accountTypeValid(String inputType) {
        for (UserType u : UserType.values()) {
            if (u.name().equals(inputType)) {
                return true;
            }
        }
        return false;
    }


    public static ArrayList<ArrayList<String>> retrieveOrders() throws SQLException {
//        Object[][] retrievedOrders = new Object[][];
//        List<Order> foundOrders = new ArrayList<>();
        ArrayList<ArrayList<String>> orderIDs = new ArrayList<>();
        String orderID = null;

        Statement statement = connection.createStatement();

        String orders = "SELECT o.orderID, a.assetName, o.orderType, o.orderStatus, o.orderTime, o.orderPrice, o.orderQuantity " +
                "FROM orders AS o LEFT JOIN assets " +
                "AS a ON o.assetID = a.assetID " +
                "WHERE userID = '" + getCurrentUser() + "';";
//        getCurrentUser()
        ResultSet orderFind = statement.executeQuery(orders);
        while (orderFind.next() == true) {
            ArrayList<String> singleList = new ArrayList<String>();
            singleList.add(orderFind.getString("o.orderID"));
            singleList.add(orderFind.getString("a.assetName"));
            singleList.add(orderFind.getString("o.orderType"));
            singleList.add(orderFind.getString("o.orderTime"));
            singleList.add(orderFind.getString("o.orderPrice"));
            singleList.add(orderFind.getString("o.orderQuantity"));
            singleList.add(orderFind.getString("o.orderStatus"));
            orderIDs.add(singleList);
        }
        return orderIDs;
    }

    public static int retrieveOrderLength() throws SQLException {
        Statement statement = connection.createStatement();
        int rows = 0;
        String orders = "SELECT count(orderID) as orderNum " +
                "FROM orders " +
                "WHERE userID = '" + getCurrentUser() + "';";
        ResultSet orderFind = statement.executeQuery(orders);
        while (orderFind.next() == true) {
            rows = Integer.parseInt(orderFind.getString("orderNum"));
        }
        return rows;
    }

    public static int getOutstandingOrders() throws SQLException {
        Statement statement = connection.createStatement();
        int outstanding = 0;
        String orders = "SELECT count(orderID) as orderNum " +
                "FROM orders " +
                "WHERE orderStatus = 'INCOMPLETE' AND userID = '" + getCurrentUser() + "' ;";
        ResultSet orderFind = statement.executeQuery(orders);
        while (orderFind.next() == true) {
            outstanding = Integer.parseInt(orderFind.getString("orderNum"));
        }
        return outstanding;
    }

//    public static List<String> retrieveOrderIDs() {
//        List<String> orderID = new ArrayList<>();
//        String order = null;
//        String orders = "SELECT orderID, a.assetName, o.orderType, o.orderTime, o.orderPrice, o.orderQuantity " +
//                "FROM orders AS o LEFT JOIN assets " +
//                "AS a ON o.assetID = a.assetID " +
//                "WHERE userID = '" + getCurrentUser() + "';";
//        ResultSet orderFind = statement.executeQuery(orders);
//        if (orderFind.next() && orderFind.getString("orderID") != null) {
//            while(orderFind.next() == true){
//                orderID = orderFind.getString("unitID");
//            }
//        }
//        return exists;
//    }
}