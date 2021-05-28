package tradingPlatform.user;

import tradingPlatform.exceptions.UserException;

import java.sql.SQLException;

public class Admin extends User{
    private String unitID;
    private UserType accountType;

    public Admin(String firstName, String lastName, String unitID, String password) throws SQLException, UserException {
        super(firstName, lastName, unitID, password, UserType.Admin);
        this.unitID = unitID;
        this.accountType = accountType;
    }

    private void addUser(String firstName, String lastName, String unitID, String password, UserType accountType) throws SQLException, UserException {
        // get the type of user - admin, lead, employee
        switch(accountType){
            case Employee:
                Employee newEmployee = new Employee(firstName,lastName,unitID,password);
                break;
            case Admin:
                Admin newAdmin = new Admin(firstName,lastName,unitID,password);
                break;
            case Lead:
                Lead newLead = new Lead(firstName,lastName,unitID,password);
                break;
            default:
                throw new UserException("Not a valid UserType");
        }
        boolean found_username = false;
        //Determine if you can find the ID in the database
        // if ... found
        // found_username = true
        if (found_username = false){
//            Statement statement = conn.createStatement();
//            statement.executeUpdate("INSERT INTO Users " + "VALUES (" + user.username, user.firstName, user.lastName, user.unitID, user.password, user.accountType")");
        }
    }

    public void addUnit(){
        //new unit
    }

    public void editCredits(){

    }

    public void editAssets(){

    }

    public void viewRequests(){

    }

}