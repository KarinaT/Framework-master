package com.epam.preproduction.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PricePage extends Page {

	public PricePage(WebDriver driver) {
		super(driver);
		
	}
	
	public void getPricePageInfo (){
		getDriver().findElement(By.xpath("//tbody/tr[1]/td[@class='n'][1]/span[@class='lgh']")).getText();
	} 

}
