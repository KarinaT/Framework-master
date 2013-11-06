package com.epam.preproduction.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainBlock extends Components{
	
	private static final String ITEM_NAME = ".name";
	private static final String ITEM_PRICE = ".price";
	
	@FindBy(css = ITEM_NAME)
	WebElement productNames;
	
	
	@FindBy(css = ITEM_NAME)
	WebElement productPrices;
	


}
