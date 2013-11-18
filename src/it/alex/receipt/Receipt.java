package it.alex.receipt;

import it.alex.saletax.Item;
import it.alex.saletax.Tax;

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
	
	public void addItem(int quantity, String description, double price){
		
		Item item = new Item();
		item.setQuantity(quantity);
		item.setDescription(description);
		item.setPrice(price);
		items.add(item);
		
	}
	
	public void calculate(){
		
		for(Item item:items){
			boolean exempt = false;
			boolean imported = false;
			double basicTaxes = 0.00;
			double importedTaxes = 0.00;
			int quantity = item.getQuantity();
			String description = item.getDescription();
			double price = item.getPrice();
			for(Tax.BASIC_SALES_EXEMPT basicSalesExempt : Tax.BASIC_SALES_EXEMPT.values()){
				boolean contain = description.toUpperCase().contains(basicSalesExempt.name());
				if(contain)
					exempt = true;
				imported = description.contains(Tax.IMPORTED);
			}
			if(!exempt){
				basicTaxes = price * Tax.BASIC_SALES;
				for(int i = 0; i < quantity; i++){
					salesTaxes += basicTaxes;
					item.setPrice(format(item.getPrice() + basicTaxes));
				}
			}
			if(imported){
				importedTaxes = ((float) Math.ceil((price * Tax.IMPORT_DUTY)/ROUND)*ROUND);
				for(int i = 0; i < quantity; i++){
					salesTaxes = salesTaxes + importedTaxes;
					item.setPrice(format(item.getPrice() + importedTaxes));
				}
			}
			for(int i = 0; i < quantity; i++){
				if(i>0){
					item.setPrice(format(item.getPrice() + price));
				}
				total += price;
			}
		}
		total += salesTaxes;
	
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
