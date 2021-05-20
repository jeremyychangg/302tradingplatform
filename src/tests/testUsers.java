package tests;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import user.UserException;

import user.User;


public class testUsers{
    /* Test 1: Testing whether the user exists */
    @BeforeEach @Test
    public void setUpUsers(){
//        users = new User();
    }

    /* Test 2: Adding new user to database. Also tests that valid values inputted. */
    @Test
    public void testNewUser() throws UserException {
        User testUser= new User("neo123", "Patrick", "Star", 4, "password123", UserType.Admin);
//        users.addUser(testUser);
//        assertEquals("blahblah123", getUser("blahblah123"), "Adding new user failed");
    }

    /* Test : Used to determine if invalid inputs can still be parsed to the database. */
    @Test
    public void testInvalidInputs() throws UserException {
        User testUser = new User(1,2,3,"",5,6);
    }

    /* Test : Used to see if user can input no password */
    @Test
    public void testNoPassword() throws UserException {
        User testUser = new User("neo123", "Patrick", "Star", 4, "", UserType.Admin);
    }

    /* Test 3: Logging in user who doesn't exist */
    @Test
    public void findUser() throws UserException {

    }

    /* Test 4: Determine if the user has permission to add user */
    @Test
    public void testPermissions() throws PermissionException {

    }

    /* Test 5: Can't add user who already exists - is unique */
    @Test
    public void duplicateUser() {
        assertThrows(UserExceptions, () -> {
            users.addUser("blahblah123");
            users.addUser("blahblah123");
        });
    }

    /* Test 6: Test that user always has a password */
    @Test
    public void passwordAssigned() {
    }

    /* Test 7: Test that user belongs to valid unit ID */
    @Test
    public void userHasID() {
        assertThrows();
    }
    /* Test 8: User has names */
    @Test
    public void userHasID() {
        assertThrows();
    }

    /* Test 9: User is only assigned to valid accountType */
    @Test
    public void userHasID() {
        assertThrows();
    }

    /* Test 10: User is only assigned to valid accountType */
    @Test
    public void userHasID() {
        assertThrows();
    }

    /* Test 11: Get credits returns actual credits of unit */
    @Test
    public void userHasID() {
        assertThrows();
    }

    /* Test 12: Check GUI matches the appropritate accountType */
    @Test
    public void userHasID() {
        assertThrows();
    }

    /* Test 13: Test that the firstname and lastname match the user ID */
    @Test
    public void userHasID() {
        assertThrows();
    }

    /* Test 14: Test credits matches updated credit balance of unit */
    @Test
    public void userHasID() {
        assertThrows();
    }

    /* Test 15: User able to change password if forgotten */
    @Test
    public void userHasID() {
        assertThrows();
    }

    /* Test 16: User able to change password */
    @Test
    public void userHasID() {
        assertThrows();
    }

    /* Test 17: User is provided notifications relevant to them */
    @Test
    public void userHasID() {
        assertThrows();
    }

    /* Test 18: Can add new IT admin users */
    /* Test 19: IT Admin given special GUI */
    /* Test 19: Only IT Admin given special permissions to add user */
    /* Test 19: Only IT Admin given special permissions to add asset */
    /* Test 19: Only IT Admin given special permissions to add unit */
    /* Test 19: Only IT Admin given special permissions to edit asset */
    /* Test 19: Only IT Admin given special permissions to view requests */
    /* Test: IT admin can view requests */


    /* Test : Login is filled */
    /* Test : Login username exists */
    /* Test : Login password matches the username */
}
