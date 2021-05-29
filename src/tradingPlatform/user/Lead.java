/**
 * @author Natalie Smith
 */
package tradingPlatform.user;


import tradingPlatform.exceptions.PasswordException;
import tradingPlatform.exceptions.UserException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static tradingPlatform.Main.connection;

public class Lead extends User {
    public String userID;

    public Lead(String firstName, String lastName, String unitID, String password) throws SQLException, UserException {
        super(firstName, lastName, unitID, password, UserType.Lead);
    }

    public void requestUser(String username, String firstName, String lastName){

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
