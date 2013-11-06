package com.epam.preproduction.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.epam.preproduction.configuration.DataProviderLayer;
import com.epam.preproduction.configuration.PropertyReader;
import com.epam.preproduction.pages.CataloguePage;

public class SortTest extends TestBase {

	/**
	 * 1. Написать тест, проверяющий функциональность работы сортировки (по цене
	 * и названию) для раздела «Холодильники» (сортировка справа вверху).
	 * Навигация в категорию должна быть выполнена со стартовой страницы.
	 **/

	@Test(dataProvider = "readFromExcel", dataProviderClass = DataProviderLayer.class)
	public void testSorting(String categoryName, String productType1)
			throws Exception {

		// System.out.println("===========testSorting("+categoryName+", "+productType1+")===========");
		goToMainPage();
		driver.findElement(By.linkText(productType1)).click();
		CataloguePage cataloguePage = PageFactory.initElements(driver, CataloguePage.class);
		cataloguePage.sortingPricesForRefrigirators();
		cataloguePage.sortingNamesForRefrigirators();
		Reporter.log(categoryName);
	}

}
