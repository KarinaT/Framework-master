package com.epam.preproduction.pages;

import org.openqa.selenium.WebDriver;

public abstract class Page {
	protected static WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

}
