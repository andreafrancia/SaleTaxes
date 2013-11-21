package it.alexceseno.item;

public class ExemptItem extends Item{
	
	public static double TAX_SALES = 0.00;
	
	public enum BASIC_SALES_EXEMPT{
		BOOK, FOOD, MEDICAL_PRODUCT, CHOCOLATE, PILL; 
	}
	
	public double addTaxSales(double startPrice) {
		System.out.print("ExemptItem[" + getDescription() + "]");
		return TAX_SALES;
	}
	
}
