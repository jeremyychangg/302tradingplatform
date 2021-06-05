/**
 * @author Natalie Smith
 */
package tradingPlatform.user;

import tradingPlatform.Asset;
import tradingPlatform.Unit;
import tradingPlatform.enumerators.UserType;
import tradingPlatform.exceptions.AssetTypeException;
import tradingPlatform.exceptions.UnitException;
import tradingPlatform.exceptions.UserException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static tradingPlatform.Main.connection;
import static tradingPlatform.passwordEncryption.verifyPassword;

public class Admin extends User{
    private String unitID;
    private UserType accountType = UserType.Employee;


    public Admin(String firstName, String lastName, String unitID, String password) throws Exception {
        super(firstName, lastName, unitID, password, UserType.Admin);
        this.unitID = unitID;
        this.accountType = accountType;
    }

    public Admin(String firstName, String lastName, String unitID) throws SQLException, UserException {
        super(firstName, lastName, unitID, UserType.Admin);
        this.unitID = unitID;
        this.accountType = accountType;
    }

    public Admin(String userID) throws SQLException {
        super(userID);
    }

    @Override
    public void addUserToDatabase(User user) throws Exception, UnitException {
        // Throw exceptions if requirements not met
        if (user.returnfirstName() == null || user.returnfirstName() == "") {
            throw new UserException("First Name cannot be null or empty.");
        }
        if (user.returnlastName() == null || user.returnlastName() == "") {
            throw new UserException("Last Name cannot be null or empty.");
        }
        if (user.returnunitID() == null || user.returnunitID() == "") {
            throw new UserException("Unit ID cannot be null or empty.");
        }
        if (!unitExists(user.returnunitID())) {
            throw new UnitException("Unit ID doesn't exist. Enter in valid unitID.");
        }
        if (user.returnpassword() == null || user.returnpassword() == "") {
            throw new UserException("Password cannot be null or empty.");
        }
        if (user.returnAccountType() == null) {
            throw new UserException("Account Type cannot be null or empty.");
        }
        if (userTypeToS(user.returnAccountType()) == null) {
            throw new UserException("Account Type not a valid Account Type.");
        }

//        Optional<String> hasedPass = hashPassword(user.returnpassword(), );

        // Based on the input for the account, set the userID initial accordingly
//        String accType = "";
//        String intialID = "";
//        switch (user.accountType) {
//            case Employee:
//                intialID = "S";
//                accType = userTypeToS(user.accountType);
//                break;
//            case Admin:
//                intialID = "A";
//                accType = userTypeToS(user.accountType);
//                break;
//            case Lead:
//                intialID = "L";
//                accType = userTypeToS(user.accountType);
//                break;
//            default:
//                throw new UserException("Not valid UserType");
//        }

//        Statement statement = connection.createStatement();
//
//        int maxUserID = 0;
//        String sqlMaxUserID
//                = "SELECT max(substring(userID, 2, 5)) as maxUserID FROM users WHERE substring(userID, 1, 1) = '"
//                + intialID + "';";
//        ResultSet getMaxID = statement.executeQuery(sqlMaxUserID);
//
//        if (getMaxID.next() && getMaxID.getString("maxUserID") != null) {
//            maxUserID = Integer.parseInt(getMaxID.getString("maxUserID"));
//        }
//
//        String newUserID = intialID + String.format("%04d", maxUserID + 1);
//        System.out.println(newUserID);
//        this.userID = newUserID;

        PreparedStatement newUser = connection.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?,?);");
        newUser.clearParameters();
        newUser.setString(1, user.returnUserID());
        newUser.setString(2, user.returnfirstName());
        newUser.setString(3, user.returnlastName());
        newUser.setString(4, user.returnunitID());
        newUser.setString(5, user.userTypeToS(accountType));
        newUser.setString(6, user.returnpassword());

        newUser.execute();
    }



    public void newUser(String firstName, String lastName, String unitID, UserType accountType) throws Exception {
        // generate a new password
        String password = "password";

        switch (accountType) {
            case Employee:
                Employee newEmployee = new Employee(firstName, lastName, unitID, password);
                break;
            case Admin:
                Admin newAdmin = new Admin(firstName, lastName, unitID, password);
                break;
            case Lead:
                Lead newLead = new Lead(firstName, lastName, unitID, password);
                break;
            default:
                throw new UserException("Not valid UserType");
        }

    }


    public void newUnit(String unitName, double creditBalance, double creditLimit) throws SQLException{
        Unit unitNew = new Unit(unitName,creditBalance, creditLimit);
    }

    public void newAsset(String assetName, String assetType) throws SQLException, AssetTypeException {
        Asset assetNew = new Asset(assetName, assetType);
    }

    public void newAsset(String assetName, String assetType, double price) throws SQLException, AssetTypeException {
        Asset assetNew = new Asset(assetName, assetType, price);
    }

    public static void editCredits(String unitID, double creditBalance) throws Exception {
        if (unitID == null || unitID == ""){
            throw new Exception("Unit ID is invalid.");
        }
        if (!unitExists(unitID)){
            throw new Exception("Unit ID doesn't exist.");
        }
        if (creditBalance < 0){
            throw new Exception("Input credit balance cannot be under 0");
        }
        String sqlAccount = "UPDATE units SET creditBalance = ? WHERE unitID = ?;";
        PreparedStatement changeAccountT = connection.prepareStatement(sqlAccount);
        changeAccountT.clearParameters();
        changeAccountT.setDouble(1, creditBalance);
        changeAccountT.setString(2, unitID);
        changeAccountT.executeUpdate();
    }

    /**
     * The method used is called by the admin to edit the type of account a user has. It requires
     * the userID and accountType input values, parsed when called, to determine if the process will
     * continue.
     * @param userID
     * @param accountType
     * @throws Exception
     */
    public static void editAccountType(String userID, String accountType) throws Exception {
        try {
            if (usernameExists(userID) && accountTypeValid(accountType)) {
                String sqlAccount = "UPDATE users SET accountType = ? WHERE userID = ?;";
                PreparedStatement changeAccountT = connection.prepareStatement(sqlAccount);
                changeAccountT.clearParameters();
                changeAccountT.setString(1, accountType);
                changeAccountT.setString(2, userID);
                changeAccountT.executeUpdate();
            }
        } catch (Exception e){
            if (userID == null || userID == ""){
                throw new Exception("User ID is invalid.");
            }
            if (accountType == null || accountType == ""){
                throw new Exception("Account type inputted is invalid.");
            }
            if (!accountTypeValid(accountType)){
                throw new Exception("Account type not found.");
            }
            if (!usernameExists(userID)){
                throw new Exception("The UserID inputted does not exist.");
            }
        }
    }



    public void editInventory(String unitID, String assetID, int quantity){
        // retrieve their current inventory storage

        // retrieve the value of their quantity
        // do some maths
        // if it is valid, then edit
        // else, throw exception and return
    }

    public void viewRequests(){

    }


    public static void changeUserPassword(String userID, String oldPassword, String newPassword, String reEnter)
            throws SQLException {
        Statement loginInput = connection.createStatement();
        // Determine if the value is a valid password
        String login = "SELECT userID, password from users WHERE userID = '" + userID + "' AND password = '" + oldPassword + "';";
        ResultSet loginResults = loginInput.executeQuery(login);

        String userReturn = null;
        String passwordReturn = null;

        while (loginResults.next()) {
            userReturn = loginResults.getString("userID");
            passwordReturn = loginResults.getString("password");
        }

        String salt = passwordReturn.substring(88);
        String passDatabase = passwordReturn.substring(0, 88);
        Boolean isCorrectPassword = verifyPassword(String.valueOf(passwordReturn), passDatabase, salt);

        // Encrypt the new password
        String encryptedPassword = encryptPassword(newPassword);

        // Update the dataset if the user is the one being edited, the old password matches the old and new password is same
        // as re-enter value
        if (userReturn.equals(userID) && isCorrectPassword && newPassword.equals(reEnter)) {
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