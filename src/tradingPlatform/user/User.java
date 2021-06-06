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
/**
 * This class is used to
 *
 * @author Natalie Smith
 */
package tradingPlatform.user;

import tradingPlatform.enumerators.UserType;
import tradingPlatform.exceptions.UserException;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static tradingPlatform.Main.*;
import static tradingPlatform.passwordEncryption.*;

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


    public static void verifyInput(String firstName, String lastName, String unitID, String password, UserType accountType) throws Exception {
        // Throw exceptions if requirements not met
        if (firstName.equals(null) || firstName.equals("") || firstName.equals(null) || firstName.equals(" ")) {
            String msg = "New User Error: First Name cannot be null or empty.";
            JOptionPane.showMessageDialog(null, msg);
            throw new UserException(msg);
        }
        if (lastName.equals(null) || lastName.equals("") || lastName.equals(null) || lastName.equals(" ")) {
            String msg = "New User Error: Last Name cannot be null or empty.";
            JOptionPane.showMessageDialog(null, msg);
            throw new UserException(msg);
        }
        if (unitID.equals(null) || unitID.equals("") || unitID.equals(null) || unitID.equals(" ")) {
            String msg = "New User Error: Unit ID cannot be null or empty.";
            JOptionPane.showMessageDialog(null, msg);
            throw new UserException(msg);
        }
        try {
            if (!unitExists(unitID)) {
                String msg = "New User Error: Unit ID" + unitID + " doesn't exist. Enter in valid unitID.";
                throw new UserException(msg);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        if (password.equals(null) || password.equals("") || password.equals(" ") || password.equals(null)) {
            String msg = "New User Error: Password cannot be null or empty.";
            JOptionPane.showMessageDialog(null, msg);
            throw new UserException(msg);
        }
        if (accountType.equals(null)) {
            String msg = "New User error: Account Type cannot be null or empty.";
            JOptionPane.showMessageDialog(null, msg);
            throw new UserException(msg);
        }
        if (userTypeToS(accountType).equals(null)) {
            String msg = "New User error: Account Type not a valid Account Type.";
            JOptionPane.showMessageDialog(null, msg);
            throw new UserException(msg);
        }
    }

    /**
     * This user constructor is the sole constructor used to initiate a new User within the database. Given that all of the
     * parameters are inputted into the program, the user is able to create a new user which - if fulfils the requirements -
     * is added to the database.
     *
     * @param firstName
     * @param lastName
     * @param unitID
     * @param password
     * @param accountType
     * @throws Exception
     */
    public User(String firstName, String lastName, String unitID, String password, UserType accountType) throws Exception {
        this.firstName = firstName;
        this.lastName = lastName;
        this.unitID = unitID;
        this.password = password;
        this.accountType = accountType;

        verifyInput(firstName, lastName, unitID, password, accountType);

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
        this.userID = newUserID;

    }


    /**
     * This function is used to get the account type of the user
     *
     * @return UserType
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
    public static String userTypeToS(UserType accInput) {
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
     * @return float
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
            return exists.equals(findUserID);
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
            try {
                if (!exists.equals(null) && exists.equals(findUnitID)) {
                    return true;
                }
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "New User Error: The inputted unit does not exist");
                throw new SQLException("New User Error: UnitID" + findUnitID + " does not currently exist.");
            }
        } catch (Exception exception) {
        }

        return false;
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
     * Given an old password, new password and re entered password string values, the changePassword method
     * would update the password of the current user to the new password provided. Within the method, it would
     * retrieve the users database given the current user, and return their current password, which would be checked
     * against the old password input to validate that they are user. If this condition is met, the old password is
     * replaced by a generated new encrypted password.
     *
     * @param newPassword
     * @param reEnter
     * @param oldPassword
     */
    public static void changePassword(String oldPassword, String newPassword, String reEnter) throws SQLException {
        Statement loginInput = connection.createStatement();
        // Determine if the value is a valid password

        String login = "SELECT userID, password from users WHERE userID = '" + getCurrentUser() + "';";
        ResultSet loginResults = loginInput.executeQuery(login);

        String userReturn = null;
        String passwordReturn = null;

        while (loginResults.next()) {
            userReturn = loginResults.getString("userID");
            passwordReturn = loginResults.getString("password");
        }
        String salt = passwordReturn.substring(88);
        String passDatabase = passwordReturn.substring(0, 88);
        Boolean isCorrectPassword = verifyPassword(oldPassword, passDatabase, salt);

        // Encrypt the new password
        String encryptedPassword = encryptPassword(newPassword);

        // Update the dataset if the user is the one being edited, the old password matches the old and new password is same
        // as re-enter value
        if (userReturn.equals(getCurrentUser()) && isCorrectPassword && newPassword.equals(reEnter)) {
            // input the SQL query for the database
            String passwordInputQuery = "UPDATE users SET password = ? WHERE userID = ?;";
            PreparedStatement updatePassword = connection.prepareStatement(passwordInputQuery);
            updatePassword.clearParameters();
            updatePassword.setString(1, encryptedPassword);
            updatePassword.setString(2, getCurrentUser());
            updatePassword.executeUpdate();
        } else {
            throw new SQLException("Password Change Error");
        }
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


    /**
     * Retrieve the order history of the associated current user, and then return
     * their associated array list of string for each of the columns. If there is
     * no related orders associated to the user at hand, then this returns an empty
     * array list.
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<ArrayList<String>> retrieveOrders() throws SQLException {
        ArrayList<ArrayList<String>> orderIDs = new ArrayList<>();
        String orderID = null;

        Statement statement = connection.createStatement();

        String orders = "SELECT o.orderID, a.assetName, o.orderType, o.orderStatus, o.orderTime, o.orderPrice, o.orderQuantity " +
                "FROM orders AS o LEFT JOIN assets " +
                "AS a ON o.assetID = a.assetID " +
                "WHERE userID = '" + getCurrentUser() + "';";
        ResultSet orderFind = statement.executeQuery(orders);
        while (orderFind.next() == true) {
            ArrayList<String> singleList = new ArrayList<>();
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


    /**
     *
     * @return
     * @throws SQLException
     */
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


    /**
     * A method used to take a string of a password and output an encrypted version of the password.
     * It uses a salt and key, generated using the passwordEncryption class.
     *
     * @param password
     * @return
     */
    public static String encryptPassword(String password) {
        // Generate a password
        String salt = generateSalt(100).get();
        String key = hashPassword(password, salt).get();
        String passwordCombo = key + salt;
        return passwordCombo;
    }
}