package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tradingPlatform.Request;
import tradingPlatform.Unit;
import tradingPlatform.exceptions.UnitException;
import tradingPlatform.requestType;
import tradingPlatform.user.User;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class testUnit {
    Unit unit1;
    Unit unit2;
    Unit unit3;

    @BeforeEach
    @Test
    void setupUnit() {
        // Unit with all fields
        unit1 = new Unit("IT00000010","IT", 20000.00, 10000.00);
        // Unit without unit ID
        unit2 = new Unit("Human Resources", 8050.28, 5000.00);
        unit3 = new Unit();
    }
    // Construct test list of users
    @BeforeEach
    @Test
    public List<User> userArray() {
        List<User> userList = new ArrayList<String>();
        userList.add("S0001");
        userList.add("L0064");
        userList.add("A0002");
        return userList;
    }

    @Test
    // - Test 1: Check that the userID provided is a string
    public void unitIDIsString() {
        assertTrue(unit1.getUnitID() == "IT00000010");
    }

    @Test
    // - Test 2: Check that the userID provided is a string
    public void unitNameIsString() {
        assertTrue(unit1.getUnitName() == "IT");
        assertTrue(unit2.getUnitName() == "Human Resources");
    }

    @Test
    // - Test 3: Check that the credit balance provided is a double
    public void CBIsString() {
        assertTrue(unit1.getCreditBalance() == 20000.00);
        assertTrue(unit2.getCreditBalance() == 8050.28);
    }

    @Test
    // - Test 4: Check that the limit provided is a double
    public void limitValid() {
        assertTrue(unit1.getLimit() == 10000);
        assertTrue(unit2.getLimit() == 5000.00);
    }

    @Test
    // - Test 5: Check that the credit balance is not less than the limit
    public void moreCreditThanLimit() {
        assertTrue(unit1.getCreditBalance() >= unit1.getLimit());
        assertTrue(unit2.getCreditBalance() >= unit2.getLimit());
    }

    @Test
    // - Test 6: Check that the credit balance is not negative
    public void sufficientCredit {
        assertTrue(unit1.getCreditBalance() >= 0.00);
        assertTrue(unit2.getCreditBalance() >= 0.00);
    }

    @Test
    // - Test 7: Check that the limit is not negative
    public void sufficientLimit {
        assertTrue(unit1.getLimit() >= 0.00);
        assertTrue(unit2.getLimit() >= 0.00);
    }
    
    /* This test was adapted from:
    https://stackoverflow.com/questions/45455246/how-to-write-assert-notnull-for-the-array-list-in-junit
    */
    @Test
    // - Test 8: Check that the list is not empty (not null)
    public void notEmptyList() {
        ArrayList<User> users = new ArrayList<>();
        for (User user: users) {
            Assert.assertNotNull(user)
        }
    }

    @Test
    // - Test 9: Check that the list returns a list of users that belong to a unit
    public void userInUnit() {
        ArrayList<User> userUnit;
        userUnit = unit1.searchUser("A0002");
    }

    @Test
    // Test 10- check when you add a unit
    public void addAUnit() throws UnitException {
        unit3.addUnit("Consulting");
        assertEquals("Non-existent team.", unit3.getUnitName("Consulting");,
                "Adding unit failed");
    }

    // check when you remove a unit
    @Test
    // Test 11- Check if unit is able to be removed
    public void remove() throws UnitException {
        unit3.deleteUnit("S0005");
        assertEquals("Non-existent team.", unit3.getUnitID("S0005");,
        "Deleting unit failed.");
    }
}