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
import user.User;
import tradingPlatform.user.UserType;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testUsers {
    // Test 0: Declaring User objects for testing purposes
    User user;
    User user1;
    User user2;

    @BeforeEach @Test
    public void setUpUsers(){
        user = new User();
        user1 = new User("neo123", "Patrick", "Star", 4, "password123", UserType.Admin);
        user2 = new User("mik434", "Michael", "Jackson", 2, "password456", UserType.Employee);
    }

    // Test 0 : Base case ID
    @Test
    public void baseUserID(){
        assertEquals(0, user._username);
    }

    // Test 1: Adding same user to database.
    @Test
    public void duplicateUser() throws UserException {
        user = new User("neo123", "Patrick", "Star", 4, "password123", UserType.Admin);
        assertEquals("neo123", user._username, "Adding same user failed");
    }

    // Test 2: Determine if invalid inputs can still be parsed to the database.
    @Test
    public void testInvalidInputs() throws UserException {
        user = new User(1,2,3,"",5,6);
    }

    // Test 3: User input no password
    @Test
    public void testNoPassword() throws UserException {
        user = new User("neo123", "Patrick", "Star", 4, "", UserType.Admin);
    }


    // Test 4: User input empty password
    @Test
    public void testEmptyPassword() throws UserException {
        user = new User("neo123", "Patrick", "Star", 4, , UserType.Admin);
    }

    // Test 5: Test that user belongs to valid unit ID
    @Test
    public void userValidID() {
        assertEquals("mik434", user2._username);
    }

    // Test 6: Test that the firstname and lastname match the user ID
    @Test
    public void userNameMatch() {
        assertEquals("Michael", user2._firstName);
        assertEquals("Jackson", user2._lastName);
    }

    // Test 7: User is only assigned to valid accountType
    @Test
    public void userValidAcType() throws UserException {
        user.setUserType(UserType.Intern);
    }

    // Test 8: Get credits returns actual credits of unit
    @Test
    public void userCreditMatch() {
        assertEquals(300, user2.getCredits());
    }

    // Test 9: Test user always has name
    @Test
    public void userSameName(){
        assertEquals("Harry", user2._firstName);
        assertEquals("Jackson", user2._firstName);
    }

    /* Test 10: User able to change password */
    @Test
    public void userChangePassword() {
        user1.changePassword("maxwell");
        assertEquals("maxwell", user1.password);
    }



    // Test 10: Test credits matches updated credit balance of unit
//    @Test
//    public void userHasID() {
//        assertThrows();
//    }


    // Additional


    // The graveyard - tests that I don't know what to do about

    // Test 6: Test that user always has a password
//    @Test
//    public void passwordAssigned() {
//        assertEquals("password456", user2._password);
//    }
}
