// 	******************************************************************************************
// 	**																        **
// 	**	Filename: testUsers.java									             **
// 	**																	   **
// 	**	Description: Testing Code for User Class							   **
// 	**													   	                  **
// 	**																	   **
// 	**	Contributors: Natalie Smith (n10462040)									   **
// 	**																	   **
// 	**																        **
// 	**	Date Created: 15/05/2021												   **
// 	**																	   **
// 	**																	   **
// 	**	Change Documentation											        **
// 	**		> Updated Version										        **
// 	**																        **
// 	**																	   **
// 	**																	   **
// 	******************************************************************************************

package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tradingPlatform.enumerators.UserType;
import tradingPlatform.exceptions.UnitException;
import tradingPlatform.user.Admin;
import tradingPlatform.user.Employee;
import tradingPlatform.user.Lead;
import tradingPlatform.user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testUsers {
    // Test 0: Declaring User objects for testing purposes
    Admin user;
    User user1;
    User user2;
    User user3;
    Employee employee;
    Admin admin;
    Lead lead;

    @BeforeEach @Test
    public void setUpUsers() throws Exception {
        Admin user = new Admin("Jennie", "Kim", "IT0001", "password");
//        Unit unit = new Unit();
    }

    @Test
    public void constructUsers() throws Exception {
        user1 = new User("Patrick", "Star", "IT0001", "password123", UserType.Admin);
        user2 = new User("Michael", "Jackson", "CA0001", "password456", UserType.Employee);
        user3 = new User("Prince", "Harry", "R0001", "royalty100", UserType.Lead);
    }

    @Test
    public void constructShort() throws Exception {
        employee = new Employee("Alice", "Spring", "BSB001", "passwordOK");
        admin = new Admin("Maxwell", "Smart", "FM0001", "password99");
        lead = new Lead("Anne", "Hathaway", "IFB295", "shakespeare");
    }

    // Admin can access the add user to database method
    @Test
    public void addingToDatabase() throws Exception, UnitException {
        user.addUserToDatabase(user1);
        user.addUserToDatabase(user2);
        user.addUserToDatabase(user3);
        user.addUserToDatabase(employee);
        user.addUserToDatabase(admin);
        user.addUserToDatabase(lead);
    }





    @Test
    public void informationCorrect() throws Exception {
        assertEquals("Patrick", user1.returnfirstName());
        assertEquals("Star", user1.returnlastName());
        assertEquals("Michael", user2.returnfirstName());
        assertEquals("Jackson", user2.returnlastName());
        assertEquals("Price", user3.returnfirstName());
        assertEquals("Harry", user3.returnlastName());
        assertEquals("Alice", employee.returnfirstName());
        assertEquals("Spring", employee.returnlastName());
        assertEquals("Maxwell", admin.returnfirstName());
        assertEquals("Smart", admin.returnlastName());
        assertEquals("Anne", lead.returnfirstName());
        assertEquals("Hathaway", lead.returnlastName());
    }

    @Test
    public void testAccType() throws Exception {
        assertEquals(UserType.Admin, user1.returnAccountType());
        assertEquals(UserType.Employee, user2.returnAccountType());
        assertEquals(UserType.Admin, user3.returnAccountType());
        assertEquals(UserType.Admin, admin.returnAccountType());
        assertEquals(UserType.Employee, employee.returnAccountType());
        assertEquals(UserType.Lead, lead.returnAccountType());
    }

    // Test 0 : Base case ID
    @Test
    public void baseUserID(){
        assertEquals("A0002", user1.returnUserID());
        assertEquals("S0001", user2.returnUserID());
        assertEquals("L0001", user3.returnUserID());
        assertEquals("S0002", employee.returnUserID());
        assertEquals("A0003", admin.returnUserID());
        assertEquals("L0002", lead.returnUserID());
    }

    @Test
    public void existingUserConstructor() throws Exception {
        User existing = new User("A0001");

        assertEquals("A0001", existing.returnUserID());
        assertEquals("Jennie", existing.returnfirstName());
        assertEquals("Kim", existing.returnlastName());
        assertEquals("IT0001", existing.returnunitID());
        assertEquals(null, existing.returnpassword());
    }

    // Test 0 : Base case ID
    @Test
    public void baseUnitID(){
        assertEquals("IT0001", user1.returnUserID());
        assertEquals("CA0001", user2.returnUserID());
        assertEquals("R0001", user3.returnUserID());
        assertEquals("BSB001", employee.returnUserID());
        assertEquals("FM0001", admin.returnUserID());
        assertEquals("IFB295", lead.returnUserID());
    }

    // Test 1: Adding same user to database.
    // see if i can do this...
    @Test
    public void duplicateUser() throws Exception {
//        user = new User("Patrick", "Star", "IT0001", "password123", UserType.Admin);
        assertEquals("neo123", user.returnUserID(), "Adding same user failed");
    }

    // Test 3: User input no password
    @Test
    public void testNoPassword() throws Exception {
//        user = new User("Patrick", "Star", "MAB102", "", UserType.Admin);
    }

    // Test : Credits for user are correct
    @Test
    public void getCredits() throws Exception {
        float credits = user2.getCredits();
        assertEquals(200.0, credits);
    }

    // Test 10: Test credits matches updated credit balance of unit
    @Test
    public void userHasID() {
//        float credits = user2.getCredits();
    }

    //double check testing this
    // Test 7: User is only assigned to valid accountType
//    @Test
//    public void userValidAcType() throws UserException {
//        user.setUserType(UserType.Intern);
//    }
}
