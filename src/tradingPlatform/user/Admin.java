/**
 * @author Natalie Smith
 */
package tradingPlatform.user;

import tradingPlatform.Asset;
import tradingPlatform.Unit;
import tradingPlatform.exceptions.AssetTypeException;
import tradingPlatform.exceptions.UserException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static tradingPlatform.Main.connection;

public class Admin extends User{
    private String unitID;
    private UserType accountType = UserType.Employee;


    public Admin(String firstName, String lastName, String unitID, String password) throws SQLException, UserException {
        super(firstName, lastName, unitID, password, UserType.Admin);
        this.unitID = unitID;
        this.accountType = accountType;
    }

    public Admin(String firstName, String lastName, String unitID) throws SQLException, UserException {
        super(firstName, lastName, unitID, UserType.Admin);
        this.unitID = unitID;
        this.accountType = accountType;
    }


    public void newUser(String firstName, String lastName, String unitID, UserType accountType) throws UserException, SQLException {
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


    public void newUnit(String unitName, double creditBalance, double creditLimit){
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

    public static void editAccountType(String userID, String accountType) throws SQLException {
        if (usernameExists(userID) && accountTypeValid(accountType)) {
            String sqlAccount = "UPDATE users SET accountType = ? WHERE userID = ?;";
            PreparedStatement changeAccountT = connection.prepareStatement(sqlAccount);
            changeAccountT.clearParameters();
            changeAccountT.setString(1, accountType);
            changeAccountT.setString(2, userID);
            changeAccountT.executeUpdate();
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

}