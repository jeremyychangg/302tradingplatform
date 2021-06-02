package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tradingPlatform.Request;
import tradingPlatform.Unit;
import tradingPlatform.requestType;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class testUnit {
    Unit unit1;
    Unit unit2;

    @BeforeEach
    @Test
    void setupUnit() {
        // Unit with all fields
        unit1 = new Unit("IT00000010","IT", 20000.00, 10000.00);
        // Unit without unit ID
        unit2 = new Unit("Human Resources", 8050.28, 5000.00);
    }
    // Construct test list of users
    @BeforeEach
    @Test
    public List<Integer> userArray() {
        List<tradingUser> userList = new ArrayList<String>();
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

    // * needs to be completed * //

    // check that the list is not empty
    // check that the list returns a list of users
    // check when you add a unit
    // check when you remove a unit

    @Test
    public void testMyArray() {
        Unit testUnit = new Unit();
        assertEquals(Arrays.asList("IT00000010"), testUnit.userList);
    }

    // - Test :
    @Test
    public void noList() {
        Unit testCases = new Unit();
        List<String> result = testCases.userArray();
        AssertNull("List shouldn't be null", result);
    }
    // - Test 3: check if the unit has a list of users
    @Test
    public void noUsers() {

    }

    // - Test 5: check if the list of users is full of strings
    @Test
    public void allStrings() {

    }

}