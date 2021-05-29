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
import static tradingPlatform.Main.getCurrentUser;

/**
 * This class is used to
 * @author Natalie Smith
 */
public class User {
    private int userID;
    private String username;
    private String firstName;
    private String lastName;
    private String unitID;
    private String password;
    private UserType accountType = UserType.Employee;

    public int getUserID(){
        return this.userID;
    }

<<<<<<< HEAD
    public User(String firstName, String lastName, String unitID, String password, UserType accountType) throws UserException, SQLException {
=======

    public User(String username, String firstName, String lastName, int unitID, String password, UserType accountType) throws UserException, SQLException {
        this.username = username;
>>>>>>> 84fced426bb5a18087cec195d92f0ab84e626242
        this.firstName = firstName;
        this.lastName = lastName;
        this.unitID = unitID;
        this.password = password;
        this.accountType = accountType;

        // Throw exceptions if requirements not met
<<<<<<< HEAD
        if(firstName == null || firstName == "") {
            throw new UserException("UserID cannot be null or empty.");
=======
        if(username == null || username == "") {
            throw new UserException("Username cannot be null or empty.");
>>>>>>> 84fced426bb5a18087cec195d92f0ab84e626242
        }
        if(firstName == null || firstName == "") {
            throw new UserException("UserID cannot be null or empty.");
        }
        if(unitID == null || unitID == "" || !unitExists(unitID)) {
            throw new UserException("Username cannot be null or empty.");
        }
        if(password == null || password == "") {
            throw new UserException("Username cannot be null or empty.");
        }
        if(accountType == null || lastName == "") {
            throw new UserException("Username cannot be null or empty.");
        }

<<<<<<< HEAD
        // Based on the input for the account, set the userID initial accordingly
        String accType = "";
        String intialID = "";
        switch(accountType){
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

=======
        int userID;
>>>>>>> 84fced426bb5a18087cec195d92f0ab84e626242
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

        PreparedStatement newUser = connection.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?,?);");
        newUser.clearParameters();
        newUser.setString(1, newUserID);
        newUser.setString(2, firstName);
        newUser.setString(3, lastName);
        newUser.setString(4, unitID);
        newUser.setString(5, accType);
        newUser.setString(6, password);

        newUser.execute();
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
     * @author Natalie Smith
     */
<<<<<<< HEAD
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
        for (UserType u : UserType.values()){
            if(u.name().equals(accountString)){
                return u;
            }
        }
        return null;
    }


    /**
     * Used to convert and return string version of a accountInput type
     * @param accInput
     * @return
     */
    public String userTypeToS(UserType accInput){
        for (UserType u : UserType.values()){
            if(u.equals(accInput)){
                return u.name();
            }
        }
        return null;
    }


=======
    public UserType getAccountType(){
        return this.accountType;
    }


>>>>>>> 84fced426bb5a18087cec195d92f0ab84e626242
    /**
     * The getCredits function is used to retrieve the credits of the user
     * @return
     */
    public static float getCredits() throws SQLException {
        // search the Units database and return the credit of the given unit
        float credits = 0.0F;
        Statement statement = connection.createStatement();
        String queryCredits = "SELECT units.creditBalance " +
                "FROM units LEFT JOIN users " +
                "ON units.unitID = users.unitID " +
<<<<<<< HEAD
                "WHERE users.userID = '" + getCurrentUser() + "';";
        ResultSet creditsBalance = statement.executeQuery(queryCredits);
        if (creditsBalance.next() && creditsBalance.getString("creditBalance") != null) {
            credits = Float.parseFloat(creditsBalance.getString("creditBalance"));
        }
        // Extract the integer value of credits
=======
                "WHERE user.userID = '" + this.userID + "';";
        ResultSet creditsBalance = statement.executeQuery(queryCredits);
        credits = Float.parseFloat(creditsBalance.getString("creditBalance"));
        // Extract the integer value of
>>>>>>> 84fced426bb5a18087cec195d92f0ab84e626242
        return credits;
    }


    /**
     * The getName function returns the first name of the user
     * @return
     */
<<<<<<< HEAD
    public static String getFirstName() throws SQLException {
        String firstName = "";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT firstName FROM users WHERE userID = '" + getCurrentUser() + "'");
        if (rs.next() && rs.getString("firstName") != null) {
            firstName = rs.getString("firstName");
        }
        System.out.println(firstName);
        return firstName;
    }


    /**
     * Get the last name of the user
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
=======
    public final String getFirstName(){
        return this.firstName;
    }


    public final String getLastName(){
        return this.lastName;
>>>>>>> 84fced426bb5a18087cec195d92f0ab84e626242
    }


    /**
     * The method is used to determine if the user inputted actually exists within the database
     * @param findUserID
     * @return
     */
<<<<<<< HEAD
    public boolean usernameExists(String findUserID) throws SQLException {
        String exists = null;
        Statement statement = connection.createStatement();
        String existUserQuery = "SELECT userID FROM users WHERE userID = " + findUserID + ";";
        ResultSet userIDFind = statement.executeQuery(existUserQuery);
        if (userIDFind.next() && userIDFind.getString("userID") != null) {
            exists = userIDFind.getString("userID");
        }
        if (exists.equals(findUserID)){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * Function used to find if a unit exists
     * @param findUnitID
     * @return
     * @throws SQLException
     */
    public boolean unitExists(String findUnitID) throws SQLException {
        String exists = null;
        Statement statement = connection.createStatement();
        String existUserQuery = "SELECT unitID FROM units WHERE unitID = '" + findUnitID + "';";
        ResultSet rs = statement.executeQuery(existUserQuery);
        if (rs.next() && rs.getString("unitID") != null) {
            exists = rs.getString("unitID");
        }
        if (exists.equals(findUnitID)){
=======
    public boolean usernameExists(int findUserID) throws SQLException {
        int exists;
        Statement statement = connection.createStatement();
        String existUserQuery = "SELECT userID FROM users WHERE userID = " + findUserID + ";";
        ResultSet userIDFind = statement.executeQuery(existUserQuery);
        exists = Integer.parseInt(userIDFind.getString("userID"));
        if (exists == findUserID){
>>>>>>> 84fced426bb5a18087cec195d92f0ab84e626242
            return true;
        }
        else{
            return false;
        }
    }



    public String GetUnitID() throws SQLException {
        String exists = null;
        Statement statement = connection.createStatement();
        String existUserQuery = "SELECT unitID FROM users WHERE userID = " + this.userID + ";";
        ResultSet userIDFind = statement.executeQuery(existUserQuery);
        if (userIDFind.next() && userIDFind.getString("unitID") != null) {
            exists = userIDFind.getString("unitID");
        }
        return exists;
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
<<<<<<< HEAD
        updatePassword.setString(2, this.userID);
=======
        updatePassword.setInt(2, userID);
>>>>>>> 84fced426bb5a18087cec195d92f0ab84e626242
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



//    public void getUserByID(String userID) throws SQLException {
//        String userDetails;
//        Statement statement = connection.createStatement();
//        String queryUser = "SELECT * FROM users WHERE userID = '" + userID + "';";
//        ResultSet userQuery = statement.executeQuery(queryUser);
//        String firstName = null;
//        String lastName = null;
//        int unitID = 0;
//        String password = null;
//        UserType accountType = null;
//
//        while(userQuery.next()){
//            firstName = userQuery.getString("firstName");
//            lastName = userQuery.getString("lastName");
//            unitID = Integer.parseInt(userQuery.getString("unitID"));
//            password = userQuery.getString("password");
//            String accountString = userQuery.getString("accountType");
//            accountType = getAccountType(accountString);
//        }
////
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.unitID = unitID;
//        this.password = password;
//        this.accountType = accountType;
//    }


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