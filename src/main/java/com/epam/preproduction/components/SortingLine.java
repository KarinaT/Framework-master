package com.epam.preproduction.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SortingLine {


	//public static final String ITEM = "item";
	//private static final String STRONG_TEXT = "strong";
	private static final String SORT_NAME = "//a[contains(@href,'sort=name')]";
	public static final String SORT_PRICE = "//a[contains(@href,'sort=price')]";
	
	@FindBy(xpath = SORT_NAME)
	private WebElement sortName;
	
	@FindBy(xpath = SORT_NAME)
	private WebElement sortPrice;

	public WebElement getSortName() {
		return sortPrice;
	}
	
	public WebElement getSortPrice() {
		return sortPrice;
	}
	
	


}
