package com.epam.preproduction.pages;

import org.openqa.selenium.WebDriver;

public class MainPage extends Page {

	public MainPage(WebDriver driver) {
		super(driver);
	}

	public static final String BASE_URL = "http://pn.com.ua";

	public static final String FIRST_ITEM = "//div[@class='item'][1]/div[@class='name']/a";
	public static final String SECOND_ITEM = "//div[@class='item'][2]/div[@class='name']/a";
	public static final String COMPARE = "//div[@class='compare-links']/span[1]";
	public static final String FIRST_COMPARE = "//div[@class='item'][1]/div[@class='compare-links']/span[1]";
	public static final String SECOND_COMPARE = "//div[@class='item'][2]/div[@class='compare-links']/span[1]";
	public static final String COMPARE_GOODS = "//div[@class='compare-links']/span[3]/a";

}