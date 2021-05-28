package tradingPlatform.user;

import tradingPlatform.exceptions.UserException;

import java.sql.SQLException;

public class Employee extends User {
    public Employee(String firstName, String lastName, String unitID, String password) throws SQLException, UserException {
        super(firstName, lastName, unitID, password, UserType.Employee);
    }
}
