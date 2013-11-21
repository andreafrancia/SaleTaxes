package it.alexceseno.receipt;

import it.alexceseno.item.ExemptItem;
import it.alexceseno.item.Item;
import it.alexceseno.util.UtilFormat;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
	
	private double salesTaxes;
	private double total;
	private List<Item> items = new ArrayList<Item>() ;
	private static final float ROUND = 0.05f;
	
	public double getSalesTaxes() {
		return UtilFormat.format(salesTaxes);
	}
	
	public double getTotal() {
		return UtilFormat.format(total);
	}
	
	public void calculate(){
		
		for(Item item:items){
			double price = item.getPrice();
			addSalesTaxes(item, price);
			addTaxesForImportedItem(item, price);
			updateTotal(item, price);
		}
		total += salesTaxes;
		
	}
	
	public void addItem(int quantity, String description, double price){
		
		Item item = new Item();
		for(ExemptItem.BASIC_SALES_EXEMPT basicSalesExempt : ExemptItem.BASIC_SALES_EXEMPT.values()){
			if(description.toUpperCase().contains(basicSalesExempt.name()))
				item = new ExemptItem();
		}
		item.setQuantity(quantity);
		item.setDescription(description);
		item.setPrice(price);
		item.setImported(description.contains(Item.IMPORTED));
		items.add(item);
		
	}

	private void updateTotal(Item item, double price) {
		
		for(int i = 0; i < item.getQuantity(); i++){
			if(i>0){
				item.setPrice(UtilFormat.format(item.getPrice() + price));
			}
			this.total += price;
		}
		
	}

	private void addTaxesForImportedItem(Item item, double price){
		
		if(item.isImported()){
			double importedTaxes = ((float) Math.ceil((price * Item.IMPORT_DUTY)/ROUND)*ROUND);
			for(int i = 0; i < item.getQuantity(); i++){
				this.salesTaxes += importedTaxes;
				item.setPrice(UtilFormat.format(item.getPrice() + importedTaxes));
			}
		}
		
	}
	
	private void addSalesTaxes(Item item, double price) {
		
		this.salesTaxes += item.addTaxSales(price);
		
	}	
		
	public List<String> print() {
		
		List<String> listItems = new ArrayList<String>();
		System.out.println("\nReceipt");
		for(Item item:items){
			int quantity = item.getQuantity();
			String description = item.getDescription();
			double price = item.getPrice();
			System.out.println(quantity + " " + description + ": " + price);
			listItems.add(String.valueOf(quantity) + " " + description + " " + String.valueOf(price));
		}
		System.out.println("SalesTaxes: " + getSalesTaxes() + "\nTotal: " + getTotal());
		return listItems;
		
	}	
	
}
