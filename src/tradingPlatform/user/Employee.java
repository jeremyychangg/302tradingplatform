package tradingPlatform.user;

public class Employee extends User {
    public Employee(String firstName, String lastName, String unitID, String password) throws Exception {
        super(firstName, lastName, unitID, password, UserType.Employee);
    }
}
