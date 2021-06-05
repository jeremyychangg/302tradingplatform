package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tradingPlatform.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class testUnit {
    Unit unit;

    // check that the list is not empty
    // check that the list returns a list of users
    // check when you add a unit
    // check when you remove a unit
    // check parameters
    // check that the creditBalance is not less than the limit
    // check that the creditBalance is not negative

    public List<Integer> userArray() {
        List<tradingUser> userList = new ArrayList<String>();
        userList.add("IT00000010");
        return userList;
    }

    // - Test 1: check if the unit exists
    @BeforeEach
    @Test
    public void setup() {
        unit = new Unit();
    }

    @Test
    public void testMyArray() {
        Unit testUnit = new Unit();
        assertEquals(Arrays.asList("IT00000010"), testUnit.userList);
    }

    // - Test 2: check if the unit returns a list
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

    // - Test 4: check if the list of users contains all integers
    @Test
    public void notIntegers() {

    }

    // - Test 5: check if the list of users is not full of strings
    @Test
    public void allStrings() {

    }

}