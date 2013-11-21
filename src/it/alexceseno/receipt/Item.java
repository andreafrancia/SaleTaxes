package it.alexceseno.receipt;

import it.alexceseno.util.MathUtils;

class Item {
	
	private int quantity;
	private String description;
	private double price;
	public static double TAX_SALES = 0.10;
	public final static double IMPORT_DUTY = 0.05;
	public final static String IMPORTED = "imported";
	private boolean imported;
	
	protected int getQuantity() {
		return quantity;
	}
	protected void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	protected String getDescription() {
		return description;
	}
	protected void setDescription(String description) {
		this.description = description;
	}
	protected double getPrice() {
		return price;
	}
	protected void setPrice(double price) {
		this.price = price;
	}
	protected boolean isImported() {
		return imported;
	}
	protected void setImported(boolean imported) {
		this.imported = imported;
	}
	
	public String toString(){
		return "quantity[" + quantity + "] description[" + description + "] price["+ price + "] imported[" + imported + "]";
	}
	
	protected double addTaxSales(double startPrice) {
		
		double basicTaxes = startPrice * Item.TAX_SALES;
		double sumSalesTaxes = 0.00;
		for(int i = 0; i < this.quantity; i++){
			sumSalesTaxes += basicTaxes;
			setPrice(MathUtils.round(this.getPrice() + basicTaxes));
		}
		return sumSalesTaxes;
	}
	
	
}
