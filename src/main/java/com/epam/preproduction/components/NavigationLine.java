package com.epam.preproduction.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationLine extends Components {
	
	public static final String PREVIOUS = ".pager-previous first";
	public static final String NEXT = ".pager-next";
	public static final String FIRST_PAGE = ".pager-item";
	public static final String LAST_PAGE = ".pager-item";
	public static final String ACTIVE_LINK = "active";

	

	@FindBy(css = PREVIOUS)
	WebElement previousItemPage;
	
	@FindBy(css = NEXT)
	WebElement nextItemPage;

	@FindBy(css = FIRST_PAGE)
	WebElement firstPage;
	
	@FindBy(css = LAST_PAGE)
	WebElement lastPage;
	
}
