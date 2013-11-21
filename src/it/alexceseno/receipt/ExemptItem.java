package it.alexceseno.receipt;


public class ExemptItem extends Item{
	
	private static double TAX_SALES = 0.00;
	
	protected enum BASIC_SALES_EXEMPT{
		BOOK, FOOD, MEDICAL_PRODUCT, CHOCOLATE, PILL; 
	}
	
	protected double addTaxSales(double startPrice) {
		return TAX_SALES;
	}
	
}
