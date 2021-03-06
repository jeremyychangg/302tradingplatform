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
import tradingPlatform.Unit;
import tradingPlatform.enumerators.UserType;
import tradingPlatform.user.Admin;
import tradingPlatform.user.Employee;
import tradingPlatform.user.Lead;
import tradingPlatform.user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class testUsers {
    // Test 0: Declaring User objects for testing purposes
    Admin user;
    User user1;
    User user2;
    User user3;
    Unit unit;
    Employee employee;
    Admin admin;
    Lead lead;

    @BeforeEach @Test
    public void setUpUsers() throws Exception {
        unit = new Unit("Software Dev", 200.0, 20.0);
        Admin user = new Admin("Jennie", "Kim", unit.getUnitID(), "password");
    }

    @Test
    public void constructUsers() throws Exception {
        user1 = new User("Patrick", "Star", "IT0001", "password123", UserType.Admin);
        user2 = new User("Michael", "Jackson", "CA0001", "password456", UserType.Employee);
        user3 = new User("Prince", "Harry", "R0001", "royalty100", UserType.Lead);
    }

    // Test data is not null

    @Test
    public void testPasswordNull() throws Exception {
        assertNotNull(user1.returnpassword());
        assertNotNull(user1.returnUserID());
        assertNotNull(user1.returnlastName());
        assertNotNull(user1.returnfirstName());
        assertNotNull(user1.returnAccountType());
    }

    @Test
    public void constructShort() throws Exception {
        employee = new Employee("Alice", "Spring", "BSB001", "passwordOK");
        admin = new Admin("Maxwell", "Smart", "FM0001", "password99");
        lead = new Lead("Anne", "Hathaway", "IFB295", "shakespeare");
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

    // Base case ID
    @Test
    public void baseUnitID(){
        assertEquals("IT0001", user1.returnUserID());
        assertEquals("CA0001", user2.returnUserID());
        assertEquals("R0001", user3.returnUserID());
        assertEquals("BSB001", employee.returnUserID());
        assertEquals("FM0001", admin.returnUserID());
        assertEquals("IFB295", lead.returnUserID());
    }

    // Adding same user to database. Able to, but different user ID (if people have same name etc.)
    @Test
    public void duplicateUser() throws Exception {
        user = new Admin("Patrick", "Star", "IT0001", "password123");
        assertEquals("A0004", user.returnUserID());
    }

    // Credits for user are correct
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
