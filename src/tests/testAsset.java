// 	******************************************************************************************
// 	**																						**	
// 	**	Filename: testAsset.java															**		
// 	**																						**					
// 	**	Description: Testing Code for Asset Class											**		
// 	**																						**		
// 	**																						**		
// 	**	Contributors: Jeremy Chang (n10462040)												**					
// 	**																						**		
// 	**																						**		
// 	**	Date Created: 13/05/2021															**		
// 	**																						**					
// 	**																						**		
// 	**	Change Documentation																**		
// 	**		> Initial Version																**		
// 	**																						**		
// 	**																						**		
// 	**																						**		
// 	******************************************************************************************


package tests;
import tradingPlatform.Asset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tradingPlatform.exceptions.NegativePriceException;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testAsset {

	//	Test 0: Declaring Asset objects for testing
	Asset asset1;
	Asset asset2;
	Asset asset3;

	@BeforeEach @Test
	public void ConstructAsset() {
		asset2 = new Asset("CPU Hours", "Computing");
		asset3 = new Asset("CPU Hours", "Computing", 34.23);
	}

	//	Check that assetID is not null
	@Test
	public void NotNullAssetID() {
		assertTrue(asset2.GetAssetID() != "");
		assertTrue(asset3.GetAssetID() != "");
	}

	//	Check that assetName is not null
	@Test
	public void NotNullAssetName() {
		assertTrue(asset2.GetAssetName() != "");
		assertTrue(asset3.GetAssetName() != "");
	}

	//	Check asset >= 0 as current price (edge case)
	@Test
	public void AssetPositivePrice() {
		assertTrue(asset1.GetPrice() >= 0);
		assertTrue(asset2.GetPrice() >= 0);
		assertTrue(asset3.GetPrice() >= 0);
	}

	// Set 0 Price (Edge Case)
	@Test
	public void SetZeroPrice() {
		asset1.setPrice(0);
		asset2.setPrice(0);
		asset3.setPrice(0);
	}

	// Set 1 Price (Edge Case)
	@Test
	public void SetOnePrice() {
		asset1.setPrice(0.0001);
		asset2.setPrice(1);
		asset3.setPrice(0.1);

		assertEquals(42.84, asset1.getPrice());
		assertEquals(42.00, asset2.getPrice());
		assertEquals(42.00, asset3.getPrice());
	}

	//	Change Asset Price
	@Test
	public void ChangeAssetPrice(){
		asset1.setPrice(42.84);
		asset2.setPrice(42);
		asset3.setPrice(42.00);

		assertEquals(42.84, asset1.getPrice());
		assertEquals(42.00, asset2.getPrice());
		assertEquals(42.00, asset3.getPrice());
	}

	// Set Negative Price (Edge Case)
	@Test
	public void SetNegativePrice() {
		assertThrows(NegativePriceException.class, () -> {
			asset1.setPrice(-0.0001);
		});
		
		assertThrows(NegativePriceException.class, () -> {
			asset2.setPrice(-1);
		});

		assertThrows(NegativePriceException.class, () -> {
			asset3.setPrice(-0.1);
		});
	}

	// Get Price of Asset
	@Test
	public void ReturnAssetPrice() {
		assertEquals(42.84, asset1.getPrice());
		assertEquals(42.00, asset2.getPrice());
		assertEquals(42.00, asset3.getPrice());
	}



}