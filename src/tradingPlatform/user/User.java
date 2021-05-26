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
import tradingPlatform.exceptions.UserException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static tradingPlatform.Main.connection;

/**
 * This class is used to
 * @author Natalie Smith
 */
public class User {
    private int userID;
    private String username;
    private String firstName;
    private String lastName;
    private int unitID;
    private String password;
    private UserType accountType = UserType.Employee;

    public int getUserID(){
        return this.userID;
    }


    public User(String username, String firstName, String lastName, int unitID, String password, UserType accountType) throws UserException, SQLException {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.unitID = unitID;
        this.password = password;
        this.accountType = accountType;

        // Throw exceptions if requirements not met
        if(username == null || username == "") {
            throw new UserException("Username cannot be null or empty.");
        }
        if(unitID == 0) {
            throw new UserException("Username cannot be null or empty.");
        }
        if(password == null || password == "") {
            throw new UserException("Username cannot be null or empty.");
        }
        if(accountType == null || lastName == "") {
            throw new UserException("Username cannot be null or empty.");
        }

        int userID;
        Statement statement = connection.createStatement();

//        // Get highest ID value existing in database
//        int maxID;
//        Statement statement = connection.createStatement();
//        String sqlMaxID
//                = "SELECT max(substring(assetID, 3, 8)) as maxID FROM assets WHERE substring(assetID, 3, 8) = '"
//                + IDsubstring + "';";
//        ResultSet getMaxID = statement.executeQuery(sqlMaxID);
//
//        // Extract string result and parse as integer
//        maxID = Integer.parseInt(getMaxID.getString("maxID"));
//
//        // Add 1 to current max ID to get new ID number for this asset and append to asset type code
//        String newID = IDsubstring + String.format("%08d", maxID + 1);
//        this.assetID = newID;
//
//        PreparedStatement newAsset =
//                connection.prepareStatement("INSERT INTO assets VALUES (?,?,?,?);");
//        newAsset.clearParameters();
//        newAsset.setString(1, newID);
//        newAsset.setString(2, assetName);
//        newAsset.setDouble(3, currentPrice);
//        newAsset.setString(4, assetType);
//
//        newAsset.execute();
    }

    public User(String username, int unitID, String password, UserType accountType) throws UserException, SQLException {
        this.username = username;
        this.unitID = unitID;
        this.password = password;
        this.accountType = accountType;

        // Throw exceptions if requirements not met
        if(username == null || username == "") {
            throw new UserException("Username cannot be null or empty.");
        }
        // Throw exception if unit ID doesn't exist
        Statement statement = connection.createStatement();
        String unitQuery = "SELECT unitID FROM units WHERE unitID = '" + userID + "';";
        ResultSet unitIDQuery = statement.executeQuery(unitQuery);
        int unitIDtest = Integer.parseInt(unitIDQuery.getString("unitID"));
        if(unitIDtest != 0) {
            throw new UserException("Unit ID doesn't exist.");
        }
        // If password not filled
        if(password == null || password == "") {
            throw new UserException("Password field cannot be empty.");
        }
        // Throw exception is account type invalid
        for (UserType u : UserType.values()){
            if(!u.name().equals(accountType)){
                throw new UserException("Account Type doesn't exist.");
            }
        }
    }


    /**
     * This function is used to get the account type of the user
     * @return
     */
    public UserType getAccountType(){
        return this.accountType;
    }


    /**
     * The getCredits function is used to retrieve the credits of the user
     * @return
     */
    public float getCredits() throws SQLException {
        // search the Units database and return the credit of the given unit
        float credits;
        Statement statement = connection.createStatement();
        String queryCredits = "SELECT units.creditBalance " +
                "FROM units LEFT JOIN users " +
                "ON units.unitID = users.unitID " +
                "WHERE user.userID = '" + this.userID + "';";
        ResultSet creditsBalance = statement.executeQuery(queryCredits);
        credits = Float.parseFloat(creditsBalance.getString("creditBalance"));
        // Extract the integer value of
        return credits;
    }


    /**
     * The getName function returns the first name of the user
     * @return
     */
    public final String getName(){
        return this.firstName;
    }


    /**
     * The method is used to determine if the user inputted actually exists within the database
     * @param findUserID
     * @return
     */
    public boolean usernameExists(int findUserID) throws SQLException {
        int exists;
        Statement statement = connection.createStatement();
        String existUserQuery = "SELECT userID FROM users WHERE userID = " + findUserID + ";";
        ResultSet userIDFind = statement.executeQuery(existUserQuery);
        exists = Integer.parseInt(userIDFind.getString("userID"));
        if (exists == findUserID){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     *
     * @param passMod
     */
    public void changePassword(String passMod) throws SQLException {
        // determine if the value is a valid password
        // input the SQL query for the database
        String passwordInputQuery = "UPDATE users SET password = ? WHERE assetID = '?';";
        PreparedStatement updatePassword = connection.prepareStatement(passwordInputQuery);
        updatePassword.clearParameters();
        updatePassword.setString(1, passMod);
        updatePassword.setInt(2, userID);
        updatePassword.executeUpdate();
    }


    /**
     * Method is used to set the type of account for the user
     * @param inputType
     */
    public void setUserType(UserType inputType){
        boolean validType = false;
        for (UserType u : UserType.values()){
            if(u.name().equals(inputType)){
                validType = true;
            }
        }
        if (validType){
            this.accountType = inputType;
        }
    }


    /**
     * Returns the assets associated with the userID
     */
//    public void returnAssets(){
//        //this.unitID
//        //take the unitID and query the orders table to determine their orders
//    }

//    public void getNotifications(){
//    }

//    public void getWatchList(){
//        //
////        return this.firstName + " " + this.lastName;
//    }
}