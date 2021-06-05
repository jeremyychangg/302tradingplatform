/**
 * @author Natalie Smith
 */
package tradingPlatform.user;


import tradingPlatform.Request;
import tradingPlatform.enumerators.UserType;
import tradingPlatform.enumerators.requestType;
import tradingPlatform.exceptions.PasswordException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static tradingPlatform.Main.connection;
import static tradingPlatform.Main.getCurrentUser;

public class Lead extends User {
    public String userID;

    public Lead(String firstName, String lastName, String unitID, String password) throws Exception {
        super(firstName, lastName, unitID, password, UserType.Lead);
    }

    public Lead(String userID) throws SQLException {
        super(userID);
    }

    public void requestNewUser(String userID, String firstName, String lastName) throws SQLException {
        String message = "User " + getCurrentUser() + "has requested a new account is made for "
                + firstName + " " + lastName + " in unit " + getUnitID() + " for a " + getAccountType() + ".";
        Request newUser = new Request(getCurrentUser(), requestType.newUserRequest, message);
    }

    public void requestNewAccType(String username, String firstName, String lastName, String unitID) throws SQLException {
        String message = "User " + getCurrentUser() + "has requested to change account type for user "
                + firstName + " " + lastName + " with username " + username
                + " in unit " + unitID + " for " + getAccountType() + " permissions.";
        Request newAccount = new Request(getCurrentUser(), requestType.newAccountTypeRequest, message);
    }

    public void requestNewUnit(String unitName, String creditBalance, String creditLimit){
        String message = "User " + getCurrentUser() + "has requested a new unit is made for " +
               unitName + " with credit balance of " + creditBalance + " and limit of " + creditLimit + ".";
        Request newUnit = new Request(getCurrentUser(), requestType.newUnitRequest, message);
    }

    public void requestChangeUnit(String userID, String firstName, String lastName, String unitID) throws SQLException {
        String message = "User " + getCurrentUser() + "has requested a user change unit for user "
                + firstName + " " + lastName + " with username " + userID
                + " from unit " + getUnitID(userID) + " to " + unitID + ".";
        Request changeUnit = new Request(getCurrentUser(), requestType.changeUnitRequest, message);
    }

    public void requestEditBalance(String unitName, String creditBalance){
        String message = "User " + getCurrentUser() + "has requested a new unit is made for " +
                unitName + " with credit balance of " + creditBalance + ".";
        Request editBalance = new Request(getCurrentUser(), requestType.editBalanceRequest, message);
    }

    public void requestEditLimit(String unitName, String creditLimit){
        String message = "User " + getCurrentUser() + "has requested a new unit is made for " +
                unitName + " with credit limit of " + creditLimit + ".";
        Request editLimit = new Request(getCurrentUser(), requestType.editLimitRequest, message);
    }

//    public void requestAssetsOwned(){
//        String message = "User " + getCurrentUser() + "has requested a new unit is made for " +
//                unitName + " with credit limit of " + creditLimit + ".";
//        Request editLimit = new Request(getCurrentUser(), requestType.newAccountTypeRequest, message);
//    }

    public void newAssetRequest(String assetname, String assetType, String currentPrice){
        String message;
        if (currentPrice != null){
            message = "User " + getCurrentUser() + "has requested a new asset is made with name " +
                    assetname + " of type " + assetType + "with current price of " + currentPrice + ".";
        }
        else {
            message = "User " + getCurrentUser() + "has requested a new asset is made with name " +
                    assetname + " of type " + assetType + ".";
        }
        Request newAsset = new Request(getCurrentUser(), requestType.newAssetRequest, message);
    }

    public void newAssetTypeRequest(String assetType){
        String message = "User " + getCurrentUser() + "has requested a new asset type for " + assetType + ".";
        Request newAssetType = new Request(getCurrentUser(), requestType.newAssetTypeRequest, message);
    }

    public void permissionRequest(String permission){
        String message = "User " + getCurrentUser() + "has requested " + permission + ".";
        Request permissionRequest = new Request(getCurrentUser(), requestType.permissionRequest, message);
    }

    public void changePassword(String userIDChange, String passMod) throws SQLException, PasswordException {
        // Throw exceptions if requirements not met
        if(userIDChange == null || userIDChange == "") {
            throw new PasswordException("User ID cannot be null or empty.");
        }
        if(passMod == null || passMod == "") {
            throw new PasswordException("Password cannot be null or empty.");
        }

        // determine if the value is a valid password
        // input the SQL query for the database
        String passwordInputQuery = "UPDATE users SET password = ? WHERE userId = '?';";
        PreparedStatement updatePassword = connection.prepareStatement(passwordInputQuery);
        updatePassword.clearParameters();
        updatePassword.setString(1, passMod);
        updatePassword.setString(2, userIDChange);
        updatePassword.executeUpdate();
    }

}
