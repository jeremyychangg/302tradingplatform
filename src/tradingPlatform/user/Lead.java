/**
 * @author Natalie Smith
 */
package tradingPlatform.user;


import tradingPlatform.Request;
import tradingPlatform.enumerators.UserType;
import tradingPlatform.enumerators.requestType;
import tradingPlatform.exceptions.RequestException;

import java.sql.SQLException;

import static tradingPlatform.Main.getCurrentUser;

/**
 * Lead class extends the User class and creates a Lead using the constructors. Given
 * that a user is a lead, the related methods can be accessed - these mostly revolvign around
 * requests.
 *
 * @version 1
 * @author Natalie Smith
 */
public class Lead extends User {
    public String userID;

    public Lead(String firstName, String lastName, String unitID, String password) throws Exception {
        super(firstName, lastName, unitID, password, UserType.Lead);
    }

    public Lead(String userID) throws SQLException {
        super(userID);
    }

    /**
     * Request new user
     * @param userID
     * @param firstName
     * @param lastName
     * @throws SQLException
     * @throws RequestException
     */
    public void requestNewUser(String userID, String firstName, String lastName) throws SQLException, RequestException {
        String message = "User " + getCurrentUser() + "has requested a new account is made for "
                + firstName + " " + lastName + " in unit " + getUnitID() + " for a " + getAccountType() + ".";
        Request newUser = new Request(getCurrentUser(), requestType.newUserRequest, message);
    }


    /**
     * Request new account type
     * @param username
     * @param firstName
     * @param lastName
     * @param unitID
     * @throws SQLException
     * @throws RequestException
     */
    public void requestNewAccType(String username, String firstName, String lastName, String unitID) throws SQLException, RequestException {
        String message = "User " + getCurrentUser() + "has requested to change account type for user "
                + firstName + " " + lastName + " with username " + username
                + " in unit " + unitID + " for " + getAccountType() + " permissions.";
        Request newAccount = new Request(getCurrentUser(), requestType.newAccountTypeRequest, message);
    }

    /**
     * Request new unit
     * @param unitName
     * @param creditBalance
     * @param creditLimit
     * @throws SQLException
     * @throws RequestException
     */
    public void requestNewUnit(String unitName, String creditBalance, String creditLimit) throws SQLException, RequestException {
        String message = "User " + getCurrentUser() + "has requested a new unit is made for " +
               unitName + " with credit balance of " + creditBalance + " and limit of " + creditLimit + ".";
        Request newUnit = new Request(getCurrentUser(), requestType.newUnitRequest, message);
    }

    /**
     * Request change unit
     * @param userID
     * @param firstName
     * @param lastName
     * @param unitID
     * @throws SQLException
     * @throws RequestException
     */
    public void requestChangeUnit(String userID, String firstName, String lastName, String unitID) throws SQLException, RequestException {
        String message = "User " + getCurrentUser() + "has requested a user change unit for user "
                + firstName + " " + lastName + " with username " + userID
                + " from unit " + getUnitID(userID) + " to " + unitID + ".";
        Request changeUnit = new Request(getCurrentUser(), requestType.changeUnitRequest, message);
    }

    /**
     * Request edit balance
     * @param unitName
     * @param creditBalance
     * @throws SQLException
     * @throws RequestException
     */
    public void requestEditBalance(String unitName, String creditBalance) throws SQLException, RequestException {
        String message = "User " + getCurrentUser() + "has requested a new unit is made for " +
                unitName + " with credit balance of " + creditBalance + ".";
        Request editBalance = new Request(getCurrentUser(), requestType.editBalanceRequest, message);
    }

    /**
     * Request edit limit
     * @param unitName
     * @param creditLimit
     * @throws SQLException
     * @throws RequestException
     */
    public void requestEditLimit(String unitName, String creditLimit) throws SQLException, RequestException {
        String message = "User " + getCurrentUser() + "has requested a new unit is made for " +
                unitName + " with credit limit of " + creditLimit + ".";
        Request editLimit = new Request(getCurrentUser(), requestType.editLimitRequest, message);
    }

//    public void requestAssetsOwned(){
//        String message = "User " + getCurrentUser() + "has requested a new unit is made for " +
//                unitName + " with credit limit of " + creditLimit + ".";
//        Request editLimit = new Request(getCurrentUser(), requestType.newAccountTypeRequest, message);
//    }

    /**
     * request new asst
     * @param assetname
     * @param assetType
     * @param currentPrice
     * @throws SQLException
     * @throws RequestException
     */
    public void newAssetRequest(String assetname, String assetType, String currentPrice) throws SQLException, RequestException {
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

    /**
     * New Asset Type request
     * @param assetType
     * @throws SQLException
     * @throws RequestException
     */
    public void newAssetTypeRequest(String assetType) throws SQLException, RequestException {
        String message = "User " + getCurrentUser() + "has requested a new asset type for " + assetType + ".";
        Request newAssetType = new Request(getCurrentUser(), requestType.newAssetTypeRequest, message);
    }

    /**
     * Permission request
     * @param permission
     * @throws SQLException
     * @throws RequestException
     */
    public void permissionRequest(String permission) throws SQLException, RequestException {
        String message = "User " + getCurrentUser() + "has requested " + permission + ".";
        Request permissionRequest = new Request(getCurrentUser(), requestType.permissionRequest, message);
    }
}
