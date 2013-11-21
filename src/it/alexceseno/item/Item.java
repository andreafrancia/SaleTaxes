package it.alexceseno.item;

import it.alexceseno.util.UtilFormat;

public class Item {
	
	private int quantity;
	private String description;
	private double price;
	public static double TAX_SALES = 0.10;
	public final static double IMPORT_DUTY = 0.05;
	public final static String IMPORTED = "imported";
	private boolean imported;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isImported() {
		return imported;
	}
	public void setImported(boolean imported) {
		this.imported = imported;
	}
	
	public String toString(){
		return "quantity[" + quantity + "] description[" + description + "] price["+ price + "] imported[" + imported + "]";
	}
	
	public double addTaxSales(double startPrice) {
		
		double basicTaxes = startPrice * Item.TAX_SALES;
		double sumSalesTaxes = 0.00;
		for(int i = 0; i < this.quantity; i++){
			sumSalesTaxes += basicTaxes;
			setPrice(UtilFormat.format(this.getPrice() + basicTaxes));
		}
		return sumSalesTaxes;
	}
	
	
}
