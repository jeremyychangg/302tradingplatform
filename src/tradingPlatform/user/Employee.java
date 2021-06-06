package tradingPlatform.user;

import tradingPlatform.enumerators.UserType;
/**
 * Employee class extends the User class and creates a Employee using the constructors.
 *
 * @version 1
 * @author Natalie Smith
 */
public class Employee extends User {
    public Employee(String firstName, String lastName, String unitID, String password) throws Exception {
        super(firstName, lastName, unitID, password, UserType.Employee);
    }
}
