package com.epam.preproduction.tests;

import java.io.File;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.epam.preproduction.configuration.DataProviderLayer;
import com.epam.preproduction.pages.ItemPage;

public class MicrowavesTest extends TestBase {

	/**
	 * 2. Сравнение товаров. Выбрать 2 первых товара из раздела "микроволновки"
	 * и сравнить их. Написать тест, который проверит функциональность сравнения
	 * (а именно: все параметры из каждого товара показываются на странице
	 * сравнения, а также проверить, что только отличающиеся характеристики
	 * выделены цветом)
	 **/

	@Test(dataProvider = "readFromExcel", dataProviderClass = DataProviderLayer.class)
	public void testCompareMicrowaves(String categoryName, String productType)
			throws Exception {
		System.out.println("===========testCompareMicrowaves(" + categoryName
				+ ", " + productType + ")===========");
		goToMainPage();
		driver.findElement(By.linkText(productType)).click();
		ItemPage itemPage = PageFactory.initElements(driver, ItemPage.class);
		itemPage.checkParameters();

	}

}
