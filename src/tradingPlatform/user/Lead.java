package tradingPlatform.user;


import tradingPlatform.exceptions.UserException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static tradingPlatform.Main.connection;

public class Lead extends User {
    private int unitID;
    private String password;
    private UserType accountType;

    public Lead(String username, String firstName, String lastName, int unitID, String password, UserType accountType) throws SQLException, UserException {
        super(username, firstName, lastName, unitID, password, UserType.Lead);
        this.unitID = unitID;
    }

    public void requestUser(String username, String firstName, String lastName){

    }

    public void changePassword(int userIDChange, String passMod) throws SQLException {
        // determine if the value is a valid password
        // input the SQL query for the database
        String passwordInputQuery = "UPDATE users SET password = ? WHERE assetID = '?';";
        PreparedStatement updatePassword = connection.prepareStatement(passwordInputQuery);
        updatePassword.clearParameters();
        updatePassword.setString(1, passMod);
        updatePassword.setInt(2, userIDChange);
        updatePassword.executeUpdate();
    }

}
