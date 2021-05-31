package tradingPlatform;

import java.util.LinkedList;

public class Unit {
    public String unitID;
    public String unitName;
    public double creditBalance;
    public double limit;

    public String newUnitID;

    // Construct an existing unit
    public Unit(String unitID, String unitName, double creditBalance, double limit) {
        this.unitID = unitID;
        this.unitName = unitName;
        this.creditBalance = creditBalance;
        this.limit = limit;
    }

    // Construct a unit to be added into the platform
    public Unit(String unitName, double creditBalance, double limit) {
        this.unitName = unitName;
        this.creditBalance = creditBalance;
        this.limit = limit;
    }

    // method for assigning new ID based on unit name's code
    public String getNewUnitID(String unitID, String unitName) {
      return newUnitID;
    }
    // method for getting / showing unitID
    public void setNewUnitID() {
        // insert into database
        this.unitID = newUnitID;
    }

    // method to return list of users
    public static LinkedList<String> usersList() {
        return new LinkedList<String>();
    }

    // method for adding unit
    public void addUnit() {
        // insert row into Unit Database
    }

    // method for removing unit
    public void removeUnit() {
        // delete row from database
    }

    // Get Unit ID
    public String getUnitID() {
        return unitID;
    }

    // Get Unit Name
    public String getUnitName() {
        return unitName;
    }

    // Set Unit Name
    public void setUnitName() {
        // update database
        this.unitName = unitName;
    }

    // Get Credit Balance
    public double getCreditBalance() {
        return creditBalance;
    }

    // Set New Credit Balance
    public void setCreditBalance() {
        // update database
        this.creditBalance = creditBalance;
    }

    // Get Limit
    public double getLimit() {
        return limit;
    }

    // Set New Limit
    public void setLimit() {
        // update database
        this.limit = limit;
    }
}
