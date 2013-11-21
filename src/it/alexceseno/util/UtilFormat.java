package it.alexceseno.util;

public class UtilFormat {

	public static double format(double cost){
		return (double)Math.round(cost * 100) / 100;
	}
}
