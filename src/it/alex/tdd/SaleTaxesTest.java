package it.alex.tdd;

import it.alex.receipt.Receipt;
import it.alex.saletax.Item;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class SaleTaxesTest {
	
	List<Item> listItems = new ArrayList<Item>();
	
	/*
	 * Test of SalesTaxes and Total. The item list is a book, a cd and a bar chocolate
	 */
	@Test
	public void purchaseOneBookOneCDOneChocolateBar() throws Exception{
		
		addItem(1, " book at ", 12.49);
		addItem(1, " music CD at ", 14.99);
		addItem(1, " chocolate bar at ", 0.85);
		
		check(1.50, 29.83, listItems);
	}
	
	/*
	 * Test of SalesTaxes and Total. The item list is an imported box of chocolate and an imported
	 * bottle of perfume
	 */
	@Test
	public void purchaseOneImportedBoxChocolatesOneImportedPerfume() throws Exception{
		
		addItem(1, " imported box of chocolates at ", 10.00);
		addItem(1, " imported bottle of perfume at ", 47.50);
		
		check(7.65, 65.15, listItems);
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
		
		addItem(1, " imported bottle of perfume at  ", 27.99);
		addItem(1, " bottle of perfume at ", 18.99);
		addItem(1, " packet of headache pills at  ", 9.75);
		addItem(1, " box of imported chocolates at ", 11.25);
		
		check(6.70, 74.68, listItems);
	}	
	
	/*
	 * Test of SalesTaxes and Total. The item list is:
	 * two imported bottle of perfume,
	 * a bottle of perfume,
	 * a packet of pills
	 * a box of imported chocolates
	 */
	@Test
	public void purchaseTwoImpPerfumeAndOnePerfumeAndOnePillsAndOneImpChocolate() throws Exception{
		
		addItem(2, " imported bottle of perfume at  ", 27.99);
		addItem(1, " bottle of perfume at ", 18.99);
		addItem(1, " packet of headache pills at  ", 9.75);
		addItem(1, " box of imported chocolates at ", 11.25);
		
		check(10.90, 106.87, listItems);
	}
	
	private void check(double expectedSalesTaxes, double expectedTotal, List<Item> items ){
		
		Receipt receipt = new Receipt();
		receipt.calculate(items);
				
		Assert.assertEquals(expectedSalesTaxes,receipt.getSalesTaxes() );
		Assert.assertEquals(expectedTotal,receipt.getTotal() );
		
		receipt.print();	
		
	}
	
	private void addItem(int quantity, String description, double price){
		
		Item item = new Item();
		item.setQuantity(quantity);
		item.setDescription(description);
		item.setPrice(price);
		listItems.add(item);
		
	}
	
}
