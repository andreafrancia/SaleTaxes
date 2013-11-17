package it.alex.saletax;

public class Tax {

	public final static double BASIC_SALES = 0.10;
	public final static double IMPORT_DUTY = 0.05;
	public final static String IMPORTED = "imported";
	
	public enum BASIC_SALES_EXEMPT{
		BOOK, FOOD, MEDICAL_PRODUCT, CHOCOLATE, PILL; 
	}
	
}
