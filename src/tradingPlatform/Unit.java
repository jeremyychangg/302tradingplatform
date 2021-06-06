package tradingPlatform;

import tradingPlatform.exceptions.MultipleRowDeletionException;
import tradingPlatform.exceptions.NegativePriceException;
import tradingPlatform.exceptions.UnitException;
import tradingPlatform.user.User;

import java.sql.*;

import static tradingPlatform.Main.connection;
import static tradingPlatform.Main.getCurrentUser;

import java.util.ArrayList;
import java.util.Random;

public class Unit {
    public String unitID;
    public String unitName;
    public double creditBalance;
    public double limit;
    ArrayList<User> usersList;
    public String unitCode = "";
    public Integer randPos;
    public Integer unitNum;
    public String unitCasing;


    public Unit(String unitID) throws SQLException{
        this.unitID = unitID;
        this.usersList = searchUser(unitID);
    }

    // Construct an existing unit
    public Unit(String unitID, String unitName, double creditBalance, double limit) {
        this.unitID = unitID;
        this.unitName = unitName;
        this.creditBalance = creditBalance;
        this.limit = limit;
    }

    // Create list of users that belong to a given unit
    public ArrayList<User> searchUser(String unitID) throws SQLException {
        ArrayList<User> userList = new ArrayList<>();

        // Find users that belong in the unit by querying database
        Statement statement = connection.createStatement();
        String queryUsers
                =   "SELECT " +
                "userID " +
                "FROM users " +
                "WHERE unitID = '" + unitID + "' "
        ;

        ResultSet retrieveUser = statement.executeQuery(queryUsers);

        while (retrieveUser.next()) {
            userList.add(new User(retrieveUser.getString("userID")));
        }

        // Return list of users by unitID
        return userList;
    }

    // return all information of unit given unitID
    public static Unit getUnit(String unitID) throws SQLException, UnitException {
        Unit currentUnit;

        Statement statement = connection.createStatement();
        String queryUnit = "SELECT * from units WHERE unitID = '" + unitID + "';";
        ResultSet unitInfo= statement.executeQuery(queryUnit);

        if (unitInfo.next() && unitInfo.getString("unitID") != null) {
            currentUnit = new Unit(
                    unitInfo.getString("unitID"),
                    unitInfo.getString("unitName"),
                    unitInfo.getDouble("creditBalance"),
                    unitInfo.getDouble("limit")
            );
        } else {
            throw new UnitException("This unit does not exist.");
        }

        return currentUnit;
    }

    // Construct a unit to be added into the platform
    public Unit(String unitName, double creditBalance, double limit) throws SQLException {
        this.unitName = unitName;
        this.creditBalance = creditBalance;
        this.limit = limit;

        // Assign unitID to unit:

        // Assign unit codes by choosing random letters in the unit name
        ArrayList<String> unitCodes = new ArrayList<>();

        Statement smt = connection.createStatement();
        String sqlUnitCodes
                = "SELECT DISTINCT substring(unitID, 1, 2) as unitCodes FROM units;";
        ResultSet getCodes= smt.executeQuery(sqlUnitCodes);

        while(getCodes.next()) {
            unitCodes.add(getCodes.getString("unitCodes"));
        }

        Random rand = new Random();
        unitCasing = unitName.toUpperCase();
        randPos = rand.nextInt(unitCasing.length());
        if (randPos == 0) {
            randPos += 1;
        }
        unitCode = unitCode + unitCasing.charAt(0) + unitCasing.charAt(randPos);

        while (unitCodes.contains(unitCode)) {
            randPos = rand.nextInt(unitCasing.length());
            if (randPos == 0) {
                randPos += 1;
            }
            unitCode = unitCode + unitCasing.charAt(0) + unitCasing.charAt(randPos);
        }

        // Get max ID number
        String sqlmaxID
                = "SELECT max(substring(unitID, 3, 8)) as maxID FROM units WHERE substring(unitID, 1, 2) = '"
                + unitCode + "';";
        ResultSet getMaxID= smt.executeQuery(sqlmaxID);

        if (getMaxID.next() && getMaxID.getString("maxID") != null) {
            unitNum = Integer.parseInt(getMaxID.getString("maxID"));
        }

        // Increment current maxID
        unitNum ++;

        // Concatenate  to create new ID
        String assignID = unitCode + String.format("%08d", unitNum.toString());
        this.unitID = assignID;

        // Add unitID, unitName, creditBalance, limit into database
        PreparedStatement addNewUnit =
                connection.prepareStatement("INSERT INTO units (unitID, unitName, creditBalance, limit) VALUES (?,?,?,?);");

        addNewUnit.setString(1, assignID);
        addNewUnit.setString(2, unitName);
        addNewUnit.setDouble(3, creditBalance);
        addNewUnit.setDouble(4, limit);

        addNewUnit.execute();
    }

    public void deleteUnit(String unitID) throws SQLException, UnitException, MultipleRowDeletionException {

        PreparedStatement deleteUnit =  connection.prepareStatement("DELETE FROM units where unitID = '?';");
        deleteUnit.clearParameters();
        deleteUnit.setString(1, unitID);
        int rowsDeleted = deleteUnit.executeUpdate();

        if (rowsDeleted == 0) {
            throw new UnitException("The unit was not removed");
        } else if (rowsDeleted > 0) {
            throw new MultipleRowDeletionException("Warning: Multiple rows were deleted from this query");
        }
    }

    public void ChangeUnitBalance(String unitID, double creditBalance) throws SQLException, NegativePriceException {
        if (creditBalance < 0) {
            throw new NegativePriceException("Asset price cannot be negative");
        } else {
            this.creditBalance += creditBalance;

            String assignNewBalance = "UPDATE units SET creditBalance = ? WHERE unitID = ?;";
            PreparedStatement updateBalance = connection.prepareStatement(assignNewBalance);
            updateBalance.clearParameters();
            updateBalance.setDouble(1, this.creditBalance);
            updateBalance.setString(2, unitID);
            updateBalance.executeUpdate();
        }
    }

    public String getUnitID() throws SQLException {
        Statement statement = connection.createStatement();
        String getUnit
                =   "SELECT " +
                "unitID " +
                "FROM units " +
                "WHERE unitID = '" + unitID + "' "
                ;
        ResultSet retrieveUnit = statement.executeQuery(getUnit);

        // ADD IN EXCEPTION //
        // if it is found, then return it, otherwise throw exception //

        // Return list of users by unitID
        return unitID;
    }

    // Get Unit Name
    public String getUnitName() throws SQLException {
        Statement statement = connection.createStatement();
        String getUnit
                =   "SELECT " +
                "unitName " +
                "FROM units " +
                "WHERE unitName = '" + unitName + "' "
                ;
        ResultSet retrieveUnit = statement.executeQuery(getUnit);

        // ADD IN EXCEPTION //
        // if it is found, then return it, otherwise throw exception //

        // Return list of users by unitID
        return unitName;
    }

    // Set Unit Name
    public void setUnitName() {
        // update database
        this.unitName = unitName;
    }

    // Get Credit Balance
    public double getCreditBalance() throws SQLException {
        Statement statement = connection.createStatement();
        String getUnit
                =   "SELECT " +
                "creditBalance " +
                "FROM units " +
                "WHERE creditBalance = '" + creditBalance + "' "
                ;
        ResultSet retrieveUnit = statement.executeQuery(getUnit);

        // ADD IN EXCEPTION //
        // if it is found, then return it, otherwise throw exception //

        // Return list of users by unitID
        return creditBalance;
    }

    // Set New Credit Balance
    public void setCreditBalance() {
        // update database
        this.creditBalance = creditBalance;
    }

    // Get Limit
    public double getLimit() throws SQLException {
        Statement statement = connection.createStatement();
        String getUnit
                =   "SELECT " +
                "limit " +
                "FROM units " +
                "WHERE limit = '" + limit + "' "
                ;
        ResultSet retrieveUnit = statement.executeQuery(getUnit);

        // ADD IN EXCEPTION //
        // if it is found, then return it, otherwise throw exception //

        // Return list of users by unitID
        return limit;
    }

    // Set New Limit
    public void setLimit() {
        // update database
        this.limit = limit;
    }
}
