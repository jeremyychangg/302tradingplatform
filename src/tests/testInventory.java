// 	******************************************************************************************
// 	**																        **	
// 	**	Filename: testInventory.java									             **		
// 	**																	   **					
// 	**	Description: Testing Code for Inventory Class							   **		
// 	**													   	                  **		
// 	**																	   **		
// 	**	Contributors: Jeremy Chang (n10462040)									   **					
// 	**																	   **		
// 	**																        **		
// 	**	Date Created: 16/05/2021												   **		
// 	**																	   **					
// 	**																	   **		
// 	**	Change Documentation											        **		
// 	**		> Initial Version											        **		
// 	**																        **		
// 	**																	   **		
// 	**																	   **		
// 	******************************************************************************************


package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import tradingPlatform.Asset;
import tradingPlatform.Inventory;

import java.util.ArrayList;

public class testInventory {

	//	Test 0: Declaring Inventory objects for testing
	Inventory inventory1;
	Inventory inventory2;

	@BeforeEach @Test
	public void ConstructInventory() {
          // Test no order ID case for initial input
		inventory1 = new Inventory("UN0001","FN0001",4);
          // New inventory entry upon order
          inventory2 = new Inventory("UN001","CA0002",6,1);
	}
//
//	//	Check that assetID is not null
//	@Test
//	public void NotNullAssetID() {
//		assertNotNUll(asset1.assetID);
//		assertTrue(asset1.GetAssetID() != NULL);
//		assertTrue(asset2.GetAssetID() != NULL);
//		assertTrue(asset3.GetAssetID() != NULL);
//	}
//
//	//	Check that assetName is not null
//	@Test
//	public void NotNullAssetName() {
//		assertNotNUll(asset1.assetName);
//		assertTrue(asset1.GetAssetName() != NULL);
//		assertTrue(asset2.GetAssetName() != NULL);
//		assertTrue(asset3.GetAssetName() != NULL);
//	}
//
//	//	Check asset >= 0 as current price (edge case)
//	@Test
//	public void AssetPositivePrice() {
//		assertTrue(asset1.GetPrice() >= 0);
//		assertTrue(asset2.GetPrice() >= 0);
//		assertTrue(asset3.GetPrice() >= 0);
//	}
//
//	//	Check that asset price is not null
//	@Test
//	public void AssetNotNullPrice() {
//		assertTrue(asset1.GetPrice() != NULL);
//		assertTrue(asset2.GetPrice() != NULL);
//		assertTrue(asset3.GetPrice() != NULL);
//	}
//
//	// Set 0 Price (Edge Case)
//	@Test
//	public void SetZeroPrice() {
//		asset1.setPrice(0);
//		asset2.setPrice(0);
//		asset3.setPrice(0);
//	}
//
//	// Set 1 Price (Edge Case)
//	@Test
//	public void SetOnePrice() {
//		asset1.setPrice(0.0001);
//		asset2.setPrice(1);
//		asset3.setPrice(0.1);
//
//		assertEquals(42.84, asset1.currentPrice);
//		assertEquals(42.00, asset2.currentPrice);
//		assertEquals(42.00, asset3.currentPrice);
//	}
//
//	//	Change Asset Price
//	@Test
//	public void ChangeAssetPrice(){
//		asset1.setPrice(42.84);
//		asset2.setPrice(42);
//		asset3.setPrice(42.00);
//
//		assertEquals(42.84, asset1.currentPrice);
//		assertEquals(42.00, asset2.currentPrice);
//		assertEquals(42.00, asset3.currentPrice);
//	}
//
//	// Set Negative Price (Edge Case)
//	@Test
//	public void SetNegativePrice() {
//		assertThrows(NegativePriceException.class, () -> {
//			asset1.setPrice(-0.0001);
//		});
//
//		assertThrows(NegativePriceException.class, () -> {
//			asset2.setPrice(-1);
//		});
//
//		assertThrows(NegativePriceException.class, () -> {
//			asset3.setPrice(-0.1);
//		});
//	}
//
//	// Get Price of Asset
//	@Test
//	public void ReturnAssetPrice() {
//		assertEquals(42.84, asset1.currentPrice);
//		assertEquals(42.00, asset2.currentPrice);
//		assertEquals(42.00, asset3.currentPrice);
//	}

	// Test search asset returns array list of asset details
	@Test
	public void FindExistingAsset() {
		ArrayList<Asset> searchedAsset;
		searchedAsset = inventory1.findAsset("UN0001");	// Search on AssetID
//		assertEquals({0, "UnnamedAsset", "Miscellaneous", 0}, searchedAsset);
	}



}