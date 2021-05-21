package tests;

import tradingPlatform.Order;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


public class testOrders {

     //	Test 0: Declaring Asset objects for testing
     Order order1;
     Order order2;


     @BeforeEach @Test
     public void ConstructOrder() {
          order1 = new Order(1,1,1);
     }
//
//     //	Test 1: Base case ID
//     @Test
//     public void defaultAssetID() {
//          assertEquals(0, asset1.assetID);
//     }
//
//     //	Test 2: Base case Name
//     @Test
//     public void defaultAssetName() {
//          assertEquals("Unnamed Asset", asset1.assetName);
//     }
//
//     //	Test 3: Base case Price
//     @Test
//     public void defaultAssetPrice() {
//          assertEquals(0, asset1.currentPrice);
//     }
//
//     //	Test 4: Base case Name
//     @Test
//     public void defaultAssetName() {
//          assertEquals("Miscellaneous", asset1.assetType);
//     }
//
//     //	Check that assetID is not null
//     @Test
//     public void NotNullAssetID() {
//          assertNotNUll(asset1.assetID);
//          assertTrue(asset1.GetAssetID() != NULL);
//          assertTrue(asset2.GetAssetID() != NULL);
//          assertTrue(asset3.GetAssetID() != NULL);
//     }
//
//     //	Check that assetName is not null
//     @Test
//     public void NotNullAssetName() {
//          assertNotNUll(asset1.assetName);
//          assertTrue(asset1.GetAssetName() != NULL);
//          assertTrue(asset2.GetAssetName() != NULL);
//          assertTrue(asset3.GetAssetName() != NULL);
//     }
//
//     //	Check asset >= 0 as current price (edge case)
//     @Test
//     public void AssetPositivePrice() {
//          assertTrue(asset1.GetPrice() >= 0);
//          assertTrue(asset2.GetPrice() >= 0);
//          assertTrue(asset3.GetPrice() >= 0);
//     }
//
//     //	Check that asset price is not null
//     @Test
//     public void AssetNotNullPrice() {
//          assertTrue(asset1.GetPrice() != NULL);
//          assertTrue(asset2.GetPrice() != NULL);
//          assertTrue(asset3.GetPrice() != NULL);
//     }
//
//     // Set 0 Price (Edge Case)
//     @Test
//     public void SetZeroPrice() {
//          asset1.setPrice(0);
//          asset2.setPrice(0);
//          asset3.setPrice(0);
//     }
//
//     // Set 1 Price (Edge Case)
//     @Test
//     public void SetOnePrice() {
//          asset1.setPrice(0.0001);
//          asset2.setPrice(1);
//          asset3.setPrice(0.1);
//
//          assertEquals(42.84, asset1.currentPrice);
//          assertEquals(42.00, asset2.currentPrice);
//          assertEquals(42.00, asset3.currentPrice);
//     }
//
//     //	Change Asset Price
//     @Test
//     public void ChangeAssetPrice(){
//          asset1.setPrice(42.84);
//          asset2.setPrice(42);
//          asset3.setPrice(42.00);
//
//          assertEquals(42.84, asset1.currentPrice);
//          assertEquals(42.00, asset2.currentPrice);
//          assertEquals(42.00, asset3.currentPrice);
//     }
//
//     // Set Negative Price (Edge Case)
//     @Test
//     public void SetNegativePrice() {
//          assertThrows(NegativePriceException.class, () -> {
//               asset1.setPrice(-0.0001);
//          });
//
//          assertThrows(NegativePriceException.class, () -> {
//               asset2.setPrice(-1);
//          });
//
//          assertThrows(NegativePriceException.class, () -> {
//               asset3.setPrice(-0.1);
//          });
//     }
//
//     // Get Price of Asset
//     @Test
//     public void ReturnAssetPrice() {
//          assertEquals(42.84, asset1.currentPrice);
//          assertEquals(42.00, asset2.currentPrice);
//          assertEquals(42.00, asset3.currentPrice);
//     }
//
//     // Test search asset returns array list of asset details
//     @Test
//     public void FindExistingAsset() {
//          ArrayList<Asset> searchedAsset;
//          searchedAsset = findAsset(0);	// Search on AssetID
////          assertEquals({0, "UnnamedAsset", "Miscellaneous", 0}, searchedAsset);
}




    /* Test 2: Adding a new movie to the list
     */
//	@Test
//	public void addAMovie() throws MovieListException {
//		movies.addMovie("Star Wars");
//		assertEquals("No rating", movies.getRating("Star Wars"),
//				"Adding movie failed");
//	}

    /* Test 3: Associating a rating with a movie
     */
//	@Test
//	public void setARating() throws MovieListException {
//		movies.addMovie("Goldfinger");
//		movies.setRating("Goldfinger", 4);
//		assertEquals("****", movies.getRating("Goldfinger"),
//				"Couldn't set a rating");
//	}

    /* Test 4: Getting a printable list of movies
     */
//	@Test
//	public void listAlphabetically() throws MovieListException {
//		String orderedListing =
//				"Casablanca\n" +
//						"Maltese Bippy, The\n" +
//						"Star Wars\n";
//		movies.addMovie("Star Wars");
//		movies.addMovie("Casablanca");
//		movies.addMovie("Maltese Bippy, The");
//		assertEquals(orderedListing, movies.getList(), "Listing not alphabetical");
//	}

    /* Test 5: Can't get a rating for an unrated movie
     */
//	@Test
//	public void nonexistentRating() {
//		assertThrows(MovieListException.class, () -> {
//			movies.getRating("The Ghost in the Invisible Bikini");
//		});
//	}

    /* Test 6: Can't add the same movie twice
     */
//	@Test
//	public void duplicateMovie() {
//		assertThrows(MovieListException.class, () -> {
//			movies.addMovie("Earth Versus the Flying Saucers");
//			movies.addMovie("Earth Versus the Flying Saucers");
//		});
//	}

    /* Test 7: Even a bomb gets a single star
     */
//	@Test
//	public void ratingTooLow() {
//		assertThrows(MovieListException.class, () -> {
//			movies.addMovie("Plan Nine From Outer Space");
//			movies.setRating("Plan Nine From Outer Space", 0);
//		});
//	}

    /* Test 8: No movie gets more than five stars
     */
//	@Test
//	public void ratingTooHigh() {
//		assertThrows(MovieListException.class, () -> {
//			movies.addMovie("Citizen Kane");
//			movies.setRating("Citizen Kane", 6);
//		});
//	}

    /* Test 9: Can't rate an unknown movie
     */
//	@Test
//	public void ratingUnknownMovie() {
//		assertThrows(MovieListException.class, () -> {
//			movies.setRating("Them!", 4);
//		});
//	}
//}

