package tradingPlatform.user;

import tradingPlatform.exceptions.UserException;

import java.sql.SQLException;

public class Employee extends User {
    public Employee(String username, String firstName, String lastName, int unitID, String password, UserType accountType) throws SQLException, UserException {
        super(username, firstName, lastName, unitID, password, UserType.Employee);
    }
}
