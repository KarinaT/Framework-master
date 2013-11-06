package com.epam.preproduction.components;

public class FiltersBlock {
	
	public static String getMinPrice(int minPrice){
		return "//div[contains(text(),'Минимальная цена')]/../div[2]/a[text()='"+minPrice+"']";
	}
	public static String getMaxPrice(int maxPrice){
		return  "//div[contains(text(),'Максимальная цена')]/../div[2]/a[text()='"+maxPrice+"']";
	}
	public static String getFunction(String functionName){
		return  "//a[contains(text(),'"+functionName+"')]";
		
	}
	public static final String FILTERED_ITEMS_COUNT = "//div[@class='total']/b";
}
