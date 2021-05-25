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
import tradingPlatform.exceptions.AssetTypeException;
import tradingPlatform.exceptions.NegativePriceException;


import java.sql.SQLException;
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
	public void ConstructAsset() throws SQLException, AssetTypeException {
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
	public void SetZeroPrice() throws SQLException, NegativePriceException {
		asset1.SetPrice(0);
		asset2.SetPrice(0);
		asset3.SetPrice(0);
	}

	// Set 1 Price (Edge Case)
	@Test
	public void SetOnePrice() throws SQLException, NegativePriceException {
		asset1.SetPrice(0.0001);
		asset2.SetPrice(1);
		asset3.SetPrice(0.1);

		assertEquals(42.84, asset1.GetPrice());
		assertEquals(42.00, asset2.GetPrice());
		assertEquals(42.00, asset3.GetPrice());
	}

	//	Change Asset Price
	@Test
	public void ChangeAssetPrice() throws SQLException, NegativePriceException {
		asset1.SetPrice(42.84);
		asset2.SetPrice(42);
		asset3.SetPrice(42.00);

		assertEquals(42.84, asset1.GetPrice());
		assertEquals(42.00, asset2.GetPrice());
		assertEquals(42.00, asset3.GetPrice());
	}

	// Set Negative Price (Edge Case)
	@Test
	public void SetNegativePrice() {
		assertThrows(NegativePriceException.class, () -> {
			asset1.SetPrice(-0.0001);
		});
		
		assertThrows(NegativePriceException.class, () -> {
			asset2.SetPrice(-1);
		});

		assertThrows(NegativePriceException.class, () -> {
			asset3.SetPrice(-0.1);
		});
	}

	// Get Price of Asset
	@Test
	public void ReturnAssetPrice() {
		assertEquals(42.84, asset1.GetPrice());
		assertEquals(42.00, asset2.GetPrice());
		assertEquals(42.00, asset3.GetPrice());
	}



}