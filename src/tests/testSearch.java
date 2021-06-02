package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tradingPlatform.Search;

public class testSearch  {

    // Declare objects for testing
    Search searchForPage;
    Search searchForAsset;
    Search searchForAssetType;

    // - Test 1: Construct searches for testing
    @BeforeEach
    @Test
    void setupSearches() {
        searchForPage = new Search("Dashboard");
        searchForAsset = new Search("Office Chairs");
        searchForAssetType = new Search("Furniture");
    }

    // - Test 2: Check that search is not null
    @Test
    public void searchNotNull() {
        assetNotNull(searchForAsset.getUserQuery());
        assertTrue(searchForAsset.getUserQuery() != null);
    }

    // - Test 3: Check that a page search returns the right results
    @Test
    public void correctPageReturned() {
        assertEquals(Dashboard(), searchForPage.getUserQuery());
    }

    // - Test 4: Check that the correct asset is returned
    @Test
    public void correctAssetReturned() {
        assertEquals("Office Chairs", searchForPage.getAssetName());
    }

    // - Test 5: Check that the correct asset type is returned
    @Test
    public void correctAssetTypeReturned() {
        assertEquals("Furniture", searchForAssetType.getAssetType());
    }
}
