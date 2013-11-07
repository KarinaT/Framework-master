package com.epam.preproduction.components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainBlock extends Components {

	private static final String ITEM_NAME = ".name";
	private static final String ITEM_PRICE = ".price";
	private static final String DIV_CLASS_ITEM = ".//div[@class='item']";
	private static final String DIV_CLASS_NAME = ".//div[@class='name']/a";
	private static final String PRICE_STRONG = ".//div[@class='price']/strong";
	public static final String CLASS_DESCRIPTION = ".//div[@class='description']";
	private static final String NEXT_PAGE = "pager-next";
	public static final String ACTIVE_NEXT_LINK = "active";

	


	@FindBy(css = ITEM_NAME)
	WebElement productNames;

	@FindBy(css = ITEM_PRICE)
	WebElement productPrices;

	@FindBy(xpath = DIV_CLASS_ITEM)
	List<WebElement> divClassItem;

	@FindBy(xpath = PRICE_STRONG)
	WebElement divClassName;

	@FindBy(xpath = DIV_CLASS_NAME)
	WebElement priceStrong;
	
	@FindBy(className = NEXT_PAGE)
	WebElement nextPage;
	


	public WebElement getProductNames() {
		return productNames;
	}

	public WebElement getProductPrices() {
		return productPrices;
	}

	public List<WebElement> getDivClassItem() {
		return divClassItem;
	}

	public WebElement getDivClassName() {
		return divClassName;
	}

	public WebElement getPriceStrong() {
		return priceStrong;
	}
	
	public WebElement getNextPage() {
		return nextPage;
	}
	


}
