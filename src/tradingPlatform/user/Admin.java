package tradingPlatform.user;

import tradingPlatform.exceptions.UserException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static tradingPlatform.Main.connection;

public class Admin extends User{
    private String userID;
    private String firstName;
    private String lastName;
    private String unitID;
    private String password;
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


    public void newUser(User user) throws UserException, SQLException {
        System.out.println(user.getUnitID());
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
        userID = newUserID;

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