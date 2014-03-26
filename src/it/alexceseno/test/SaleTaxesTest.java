package it.alexceseno.test;

import it.alexceseno.receipt.Receipt;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class SaleTaxesTest {
	
	private Receipt receipt;
	
	@Before
	public void setup() {
		receipt = new Receipt();
	}
	
	/*
	 * SalesTaxes Test. The item list is a book, a cd and a bar chocolate
	 */
	@Test
	public void purchaseOneBookOneCDOneChocolateBarSalesTaxes() throws Exception{
		
		addOneBookOneCDOneChocolateBar();
		
		checkSalesTaxes(1.50);
	}
		
	/*
	 * Total test. The item list is a book, a cd and a bar chocolate
	 */
	@Test
	public void purchaseOneBookOneCDOneChocolateBarTotal() throws Exception{
		
		addOneBookOneCDOneChocolateBar();
		
		checkTotal(29.83);
	}
	
	private void addOneBookOneCDOneChocolateBar() throws Exception{
		
		receipt.addItem(1, "book", 12.49);
		receipt.addItem(1, "music CD", 14.99);
		receipt.addItem(1, "chocolate bar", 0.85);
	}
	
	/*
	 * SalesTaxes Test. The item list is an imported box of chocolate and an imported
	 * bottle of perfume
	 */
	@Test
	public void purchaseOneImportedBoxChocolatesOneImportedPerfumeSalesTaxes() throws Exception{
		
		addOneImportedBoxChocolatesOneImportedPerfume();
		
		checkSalesTaxes(7.65);
	}
	
	/*
	 * Total test. The item list is an imported box of chocolate and an imported
	 * bottle of perfume
	 */
	@Test
	public void purchaseOneImportedBoxChocolatesOneImportedPerfumeTotal() throws Exception{
		
		addOneImportedBoxChocolatesOneImportedPerfume();
		
		checkTotal(65.15);
	}
	
	private void addOneImportedBoxChocolatesOneImportedPerfume() throws Exception{
		
		receipt.addItem(1, "imported box of chocolates", 10.00);
		receipt.addItem(1, "imported bottle of perfume", 47.50);
	}
	
	/*
	 * SalesTaxes test. The item list is:
	 * an imported bottle of perfume,
	 * a bottle of perfume,
	 * a packet of pills
	 * a box of imported chocolates
	 */
	@Test
	public void purchaseOneImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolateSalesTaxes() throws Exception{
		
		addOneImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolate();
		
		checkSalesTaxes(6.70);
	}	
	
	/*
	 * Total test. The item list is:
	 * an imported bottle of perfume,
	 * a bottle of perfume,
	 * a packet of pills
	 * a box of imported chocolates
	 */
	@Test
	public void purchaseOneImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolateTotal() throws Exception{
		
		addOneImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolate();
		
		checkTotal(74.68);
	}	
	
	private void addOneImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolate() throws Exception{
		
		receipt.addItem(1, "imported bottle of perfume", 27.99);
		receipt.addItem(1, "bottle of perfume", 18.99);
		receipt.addItem(1, "packet of headache pills", 9.75);
		receipt.addItem(1, "box of imported chocolates", 11.25);
	}

	/*
	 * SalesTaxes test. The item list is:
	 * two imported bottle of perfume,
	 * a bottle of perfume,
	 * a packet of pills
	 * a box of imported chocolates
	 */
	@Test
	public void purchaseTwoImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolateSalesTaxes() throws Exception{
		
		addTwoImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolate();
		
		checkSalesTaxes(10.90);
	}
	
	/*
	 * Total test. The item list is:
	 * two imported bottle of perfume,
	 * a bottle of perfume,
	 * a packet of pills
	 * a box of imported chocolates
	 */
	@Test
	public void purchaseTwoImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolateTotal() throws Exception{
		
		addTwoImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolate();
		
		checkTotal(106.87);		
	}
	
	/*
	 * Print test. The item list is:
	 * two imported bottle of perfume,
	 * a bottle of perfume,
	 * a packet of pills
	 * a box of imported chocolates
	 */
	@Test
	public void purchaseTwoImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolatePrint() throws Exception{
		
		addTwoImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolate();
		
		receipt.calculate();
		
		List<String> items = receipt.print();
		
		Assert.assertTrue(items.get(0).contains("2"));
		Assert.assertTrue(items.get(0).contains("imported bottle of perfume"));
		Assert.assertTrue(items.get(0).contains("64.38"));
		
		Assert.assertTrue(items.get(1).contains("1"));
		Assert.assertTrue(items.get(1).contains("bottle of perfume"));
		Assert.assertTrue(items.get(1).contains("20.89"));
		
		Assert.assertTrue(items.get(2).contains("1"));
		Assert.assertTrue(items.get(2).contains("packet of headache pills"));
		Assert.assertTrue(items.get(2).contains("9.75"));
		
		Assert.assertTrue(items.get(3).contains("1"));
		Assert.assertTrue(items.get(3).contains("box of imported chocolates"));
		Assert.assertTrue(items.get(3).contains("11.85"));
		
	}
	
	private void addTwoImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolate() throws Exception{
		
		receipt.addItem(2, "imported bottle of perfume", 27.99);
		receipt.addItem(1, "bottle of perfume", 18.99);
		receipt.addItem(1, "packet of headache pills", 9.75);
		receipt.addItem(1, "box of imported chocolates", 11.25);
	}
	
	private void checkSalesTaxes(double expectedSalesTaxes){
		
		receipt.calculate();
				
		Assert.assertEquals(expectedSalesTaxes, receipt.getSalesTaxes());
		
	}
	
	private void checkTotal(double expectedTotal){
		
		receipt.calculate();
				
		Assert.assertEquals(expectedTotal, receipt.getTotal());
		
	}	

}
