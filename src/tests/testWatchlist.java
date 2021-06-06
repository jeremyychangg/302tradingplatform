package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.*;
import tradingPlatform.Asset;
import tradingPlatform.Watchlist;
import tradingPlatform.exceptions.InvalidAssetException;
import tradingPlatform.exceptions.UnitException;
import tradingPlatform.user.User;

import java.util.ArrayList;
import java.util.List;

public class testWatchlist{
    Watchlist watchlist;

    @BeforeEach
    @Test
    void setupWatchList() {
        watchlist = new Watchlist("IT00000001");
    }

    // Construct test list of users
    @BeforeEach
    @Test
    public List<Asset> assetArray() {
        List<Asset> assetList = new ArrayList<String>();
        userList.add("OS00000003");
        userList.add("FN00000005");
        userList.add("TC00000002");
        return assetList;
    }

    // - Test 1: Test add to watch list method
    @Test
    public void addsToWatchlist() throws InvalidAssetException {
        watchlist.addsToWatchlist("CA00000001");
        assertEquals(watchlist.GetAssetID() == "CA00000001");
    }

    // - Test 2: Test remove from watch list method
    @Test
    public void addsToWatchlist() throws InvalidAssetException {
        watchlist.removedFromWatchlist("OS00000003");
        assertFalse(watchlist.GetAssetID() == "OS00000003");
    }

    // - Test 3: Test watch list returns a list of assets - invalid asset exception
    @Test
    public void assetInWatchlist() {
        ArrayList<Asset> assetInList;
        assetInList = unit1.GetAssetID("OS00000003");
    }

    // - Test 4: Test that unitID is a string - unit exception
    @Test
    public void userIDIsString() {
        assertTrue(userID.getUnitName() == "IT00000001");
    }

    // - Test 5: Test that assetID is a string - asset exception
    @Test
    public void assetIDIsString() {
        assertTrue(assetID.GetAssetID() == "OS00000003");
    }

    /* This test was adapted from:
    https://stackoverflow.com/questions/45455246/how-to-write-assert-notnull-for-the-array-list-in-junit
    */
    @Test
    // - Test 6: Check that the list is not empty (not null)
    public void notEmptyList() {
        ArrayList<Asset> assets = new ArrayList<>();
        for (Asset asset: assets) {
            Assert.assertNotNull(asset)
        }
    }

}

