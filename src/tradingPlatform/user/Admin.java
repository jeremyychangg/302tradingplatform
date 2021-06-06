/**
 * @author Natalie Smith
 */
package tradingPlatform.user;

import tradingPlatform.Asset;
import tradingPlatform.Unit;
import tradingPlatform.enumerators.UserType;
import tradingPlatform.exceptions.AssetTypeException;
import tradingPlatform.exceptions.UserException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static tradingPlatform.Main.connection;

public class Admin extends User {
    private String userID;
    private String unitID;


    public Admin(String firstName, String lastName, String unitID, String password) throws Exception {
        super(firstName, lastName, unitID, password, UserType.Admin);
        this.unitID = unitID;
    }

    public Admin(String userID) throws SQLException {
        super(userID);
    }


    /**
     * Method used to add a user to the database.
     *
     * @param newUser
     * @throws Exception
     * @throws UserException
     */
    public static void addUserToDatabase(User newUser) throws Exception, UserException {
        try {
            // Verify that the user fulfils each of the requirements
            verifyInput(newUser.returnfirstName(), newUser.returnlastName(), newUser.returnunitID(), newUser.returnpassword(), newUser.returnAccountType());

            // encrypt new password
            String newPassword = encryptPassword(newUser.returnpassword());

            // Insert the data into the server
            PreparedStatement newUserQuery = connection.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?,?);");
            newUserQuery.clearParameters();
            newUserQuery.setString(1, newUser.returnUserID());
            newUserQuery.setString(2, newUser.returnfirstName());
            newUserQuery.setString(3, newUser.returnlastName());
            newUserQuery.setString(4, newUser.returnunitID());
            newUserQuery.setString(5, userTypeToS(newUser.returnAccountType()));
            newUserQuery.setString(6, newPassword);

            newUserQuery.execute();
        } catch (UserException e) {
            throw new UserException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    /**
     * The method used is called by the admin to edit the type of account a user has. It requires
     * the userID and accountType input values, parsed when called, to determine if the process will
     * continue. Throws Exception if unable to change the user field.
     *
     * @param userID
     * @param accountType
     * @throws Exception
     * @throws UserException
     */
    public static void editAccountType(String userID, String accountType) throws Exception, UserException {
        if (userID.equals(null) || userID.equals("")) {
            throw new UserException("User ID is invalid.");
        }
        if (accountType.equals(null) || accountType.equals("")) {
            throw new Exception("Account type inputted is invalid.");
        } else {
            try {
                if (usernameExists(userID) && accountTypeValid(accountType)) {
                    String sqlAccount = "UPDATE users SET accountType = ? WHERE userID = ?;";
                    PreparedStatement changeAccountT = connection.prepareStatement(sqlAccount);
                    changeAccountT.clearParameters();
                    changeAccountT.setString(1, accountType);
                    changeAccountT.setString(2, userID);
                    changeAccountT.executeUpdate();
                }
            } catch (SQLException e) {
                throw new UserException(e.getMessage());
            }
        }
    }


    /**
     * Change the user password given a user ID and a valid new password input. Throws SQL exception and user
     * exception
     *
     * @param userID
     * @param newPassword
     * @throws SQLException
     * @throws UserException
     */
    public static void changeUserPassword(String userID, String newPassword) throws SQLException, UserException {
        if (userID.equals(null) || userID.equals(" ") || userID.equals("")) {
            throw new UserException("User ID cannot be empty.");
        }
        if (newPassword.equals(null) || newPassword.equals(" ") || newPassword.equals("")) {
            throw new UserException("User " + userID + " cannot have empty password.");
        } else {
            Statement loginInput = connection.createStatement();
            // Determine if the value is a valid password
            String login = "SELECT userID from users WHERE userID = '" + userID + "';";
            ResultSet loginResults = loginInput.executeQuery(login);

            String userReturn = null;

            while (loginResults.next()) {
                userReturn = loginResults.getString("userID");
            }
            // Encrypt the new password
            String encryptedPassword = encryptPassword(newPassword);

            // Update the dataset if the user is the one being edited, the old password matches the old and new password is same
            // as re-enter value
            if (userReturn.equals(userID)) {
                // input the SQL query for the database
                String passwordInputQuery = "UPDATE users SET password = ? WHERE userID = ?;";
                PreparedStatement updatePassword = connection.prepareStatement(passwordInputQuery);
                updatePassword.clearParameters();
                updatePassword.setString(1, encryptedPassword);
                updatePassword.setString(2, userID);
                updatePassword.executeUpdate();
            }
        }
    }

    public void newUnit(String unitName, double creditBalance, double creditLimit) throws SQLException {
        Unit unitNew = new Unit(unitName, creditBalance, creditLimit);
    }

    public void newAsset(String assetName, String assetType) throws SQLException, AssetTypeException {
        Asset assetNew = new Asset(assetName, assetType);
    }

    public void newAsset(String assetName, String assetType, double price) throws SQLException, AssetTypeException {
        Asset assetNew = new Asset(assetName, assetType, price);
    }

    public static void editCredits(String unitID, double creditBalance) throws Exception {
        if (unitID == null || unitID == "") {
            throw new Exception("Unit ID is invalid.");
        }
        if (!unitExists(unitID)) {
            throw new Exception("Unit ID doesn't exist.");
        }
        if (creditBalance < 0) {
            throw new Exception("Input credit balance cannot be under 0");
        }
        String sqlAccount = "UPDATE units SET creditBalance = ? WHERE unitID = ?;";
        PreparedStatement changeAccountT = connection.prepareStatement(sqlAccount);
        changeAccountT.clearParameters();
        changeAccountT.setDouble(1, creditBalance);
        changeAccountT.setString(2, unitID);
        changeAccountT.executeUpdate();
    }


    public void editInventory(String unitID, String assetID, int quantity) {
        // retrieve their current inventory storage

        // retrieve the value of their quantity
        // do some maths
        // if it is valid, then edit
        // else, throw exception and return
    }

    public void viewRequests() {

    }


}