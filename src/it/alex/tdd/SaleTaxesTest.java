package it.alex.tdd;

import it.alex.receipt.Receipt;

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
	 * Test of SalesTaxes and Total. The item list is a book, a cd and a bar chocolate
	 */
	@Test
	public void purchaseOneBookOneCDOneChocolateBar() throws Exception{
		
		receipt.addItem(1, "book", 12.49);
		receipt.addItem(1, "music CD", 14.99);
		receipt.addItem(1, "chocolate bar", 0.85);
		
		check(1.50, 29.83);
	}
	
	/*
	 * Test of SalesTaxes and Total. The item list is an imported box of chocolate and an imported
	 * bottle of perfume
	 */
	@Test
	public void purchaseOneImportedBoxChocolatesOneImportedPerfume() throws Exception{
		
		receipt.addItem(1, "imported box of chocolates", 10.00);
		receipt.addItem(1, "imported bottle of perfume", 47.50);
		
		check(7.65, 65.15);
	}
	
	/*
	 * Test of SalesTaxes and Total. The item list is:
	 * an imported bottle of perfume,
	 * a bottle of perfume,
	 * a packet of pills
	 * a box of imported chocolates
	 */
	@Test
	public void purchaseOneImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolate() throws Exception{
		
		receipt.addItem(1, "imported bottle of perfume", 27.99);
		receipt.addItem(1, "bottle of perfume", 18.99);
		receipt.addItem(1, "packet of headache pills", 9.75);
		receipt.addItem(1, "box of imported chocolates", 11.25);
		
		check(6.70, 74.68);
	}	
	
	/*
	 * Test the items print.Test of SalesTaxes and Total. 
	 * The item list is:
	 * two imported bottle of perfume,
	 * a bottle of perfume,
	 * a packet of pills
	 * a box of imported chocolates
	 */
	@Test
	public void purchaseTwoImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolate() throws Exception{
		
		receipt.addItem(2, "imported bottle of perfume", 27.99);
		receipt.addItem(1, "bottle of perfume", 18.99);
		receipt.addItem(1, "packet of headache pills", 9.75);
		receipt.addItem(1, "box of imported chocolates", 11.25);
		
		check(10.90, 106.87);
		
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
	
	private void check(double expectedSalesTaxes, double expectedTotal){
		
		receipt.calculate();
				
		Assert.assertEquals(expectedSalesTaxes, receipt.getSalesTaxes());
		Assert.assertEquals(expectedTotal, receipt.getTotal());
		
		receipt.print();
	}

}
