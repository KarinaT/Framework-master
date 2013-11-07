package com.epam.preproduction.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FiltersBlock {

	public static String getMinPrice(int minPrice) {
		return "//div[contains(text(),'Минимальная цена')]/../div[2]/a[text()='" + minPrice + "']";
	}

	public static String getMaxPrice(int maxPrice) {
		return "//div[contains(text(),'Максимальная цена')]/../div[2]/a[text()='"+ maxPrice + "']";
	}

	public static String getFunction(String functionName) {
		return "//a[contains(text(),'" + functionName + "')]";

	}

	public static final String FILTERED_ITEMS_COUNT = "//div[@class='total']/b";

	public static final String SEARCH_PARAMETER = "Регулировка веса";

	@FindBy(className = SEARCH_PARAMETER)
	WebElement searchParameter;

	public WebElement getSearchParameter() {
		return searchParameter;
	}
}
