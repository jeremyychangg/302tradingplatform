package tradingPlatform.user;

import tradingPlatform.exceptions.UserException;

import java.sql.SQLException;

public class Admin extends User{
    private String unitID;
    private UserType accountType;

    public Admin(String firstName, String lastName, String unitID, String password, UserType accountType) throws SQLException, UserException {
        super(firstName, lastName, unitID, password, accountType);
        this.unitID = unitID;
        this.accountType = accountType;
    }

    public void addUser(User user){
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