package it.alexceseno.receipt;

import it.alexceseno.item.ExemptItem;
import it.alexceseno.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
	
	private double salesTaxes;
	private double total;
	private List<Item> items = new ArrayList<Item>() ;
	private static final float ROUND = 0.05f;
	
	public double getSalesTaxes() {
		return format(salesTaxes);
	}
	
	public double getTotal() {
		return format(total);
	}
	
	private double format(double cost){
		return (double)Math.round(cost * 100) / 100;
	}
		
	public void calculate(){
		
		for(Item item:items){
			double price = item.getPrice();
			checkExempt(item, price);
			checkImported(item, price);
			checkQuantity(item, price);
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

	private void checkQuantity(Item item, double price) {
		
		for(int i = 0; i < item.getQuantity(); i++){
			if(i>0){
				item.setPrice(format(item.getPrice() + price));
			}
			this.total += price;
		}
		
	}

	private void checkImported(Item item, double price){
		
		if(item.isImported()){
			double importedTaxes = ((float) Math.ceil((price * Item.IMPORT_DUTY)/ROUND)*ROUND);
			for(int i = 0; i < item.getQuantity(); i++){
				this.salesTaxes += importedTaxes;
				item.setPrice(format(item.getPrice() + importedTaxes));
			}
		}
		
	}
	
	private void checkExempt(Item item, double price) {
		
		if(!(item instanceof ExemptItem)){
			double basicTaxes = price * Item.TAX_SALES;
			for(int i = 0; i < item.getQuantity(); i++){
				this.salesTaxes += basicTaxes;
				item.setPrice(format(item.getPrice() + basicTaxes));
			}
		}	
		
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
