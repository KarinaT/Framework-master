package com.epam.preproduction.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.epam.preproduction.configuration.DataProviderLayer;
import com.epam.preproduction.pages.CataloguePage;

/**
 * 3. Проверить функциональность фильтра по цене (мин, макс) для категории
 * "стиральные машины". То есть, убедиться, что этот фильтр работает корректно -
 * показывает все товары, которые должны показаться и не показывает ничего
 * лишнего.
 * **/
public class WashingMachinesTest extends TestBase {

	@Test(dataProvider = "readFromExcel", dataProviderClass = DataProviderLayer.class)
	public void testFilterPrice(String categoryName, String productType,
			double minPrice, double maxPrice) throws Exception {
		System.out.println("==============testFilterPrice(" + categoryName
				+ ", " + productType + ")==============");
		goToMainPage();

		driver.findElement(By.linkText(categoryName)).click();
		driver.findElement(By.linkText(productType)).click();
		CataloguePage cataloguePage = PageFactory.initElements(driver,
				CataloguePage.class);
		cataloguePage.checkPriceFilterForWashingMachines(minPrice, maxPrice);
	}

}
