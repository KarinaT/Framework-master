package com.epam.preproduction.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.epam.preproduction.components.CompareBlock;
import com.epam.preproduction.entities.Microwave;

public class ItemPage extends Page {

	public ItemPage(WebDriver driver) {
		super(driver);
	}

	public Microwave grabAllCharacteristics() {
		Microwave microwave = new Microwave();
		Map<String, String> itemMap = new HashMap<String, String>();

		List<WebElement> listOfCharacteristics = getDriver().findElements(
				By.className("row"));
		for (WebElement element : listOfCharacteristics) {
			String charateristicName = element.findElement(By.className("pr"))
					.getText();
			String charateristicValue = element
					.findElement(By.className("val")).getText();
			itemMap.put(charateristicName, charateristicValue);
		}
		microwave.setCharacteristics(itemMap);
		return microwave;
	}

	
	public void checkParameters() {
		getDriver().findElement(By.xpath(MainPage.FIRST_ITEM)).click();
		getDriver().findElement(By.xpath(MainPage.COMPARE)).click();
		ItemPage itemPage1 = PageFactory.initElements(getDriver(),
				ItemPage.class);
		Microwave microwave1 = itemPage1.grabAllCharacteristics();
		System.out.println(microwave1.getCharacteristics());
		getDriver().navigate().back();
		refreshLocators();

		getDriver().findElement(By.xpath(MainPage.SECOND_ITEM)).click();
		getDriver().findElement(By.xpath(MainPage.COMPARE)).click();
		ItemPage itemPage2 = PageFactory.initElements(getDriver(),
				ItemPage.class);
		Microwave microwave2 = itemPage2.grabAllCharacteristics();

		System.out.println(microwave2.getCharacteristics());
		// driver.navigate().back();
		// refreshLocators();

		getDriver().findElement(By.xpath(MainPage.COMPARE_GOODS)).click();

		ComparePage comparePage = PageFactory.initElements(getDriver(),
				ComparePage.class);

		Set<String> paramsNames = comparePage.grabAllParamNames();
		Set<String> names1 = microwave1.getCharacteristics().keySet();
		Set<String> names2 = microwave2.getCharacteristics().keySet();

		System.out.println(paramsNames);
		System.out.println(names1);
		System.out.println(names2);
		if (!paramsNames.containsAll(names1)) {
			Assert.fail();
		}
		if (!paramsNames.containsAll(names2)) {
			Assert.fail();
		}

		WebElement table = getDriver().findElement(
				By.className(CompareBlock.CLASS_COMPARE));
		List<WebElement> differentItems = table.findElements(By
				.className(CompareBlock.DIFFERENT));
		for (WebElement item : differentItems) {
			List<WebElement> tds = item.findElements(By
					.tagName(CompareBlock.TD_COMPARE));
			for (WebElement td : tds) {
				if (!td.getCssValue(CompareBlock.BACKGROUND_COLOR)
						.equalsIgnoreCase(CompareBlock.BG_VALUE)) {
					Assert.fail();
				}
			}

		}
	}

	private void refreshLocators() {
		PageFactory.initElements(getDriver(), CataloguePage.class);
	}

	public void gerUrls(List<String> catalogueLinks) {
		List<String> urlList = new ArrayList<String>();
		List<String> itemNames = new ArrayList<String>();
		for (int i = 1; i < 6; i++) {
			List<WebElement> names = getDriver().findElements(By.xpath("//div[@class='item'][" + i + "]/div/a"));
	
			for (WebElement webElement : names) {
				String hrefs = webElement.getAttribute("href");
				catalogueLinks.add(hrefs);
				//itemNames.add(webElement.getTagName());
			}
			getDriver().findElement(By.xpath("//div[@class='item'][" + i + "]/div/a")).click();
			PageFactory.initElements(getDriver(), ItemPage.class);
			urlList.add(i-1, getDriver().getCurrentUrl());
			getDriver().navigate().back();
			refreshLocators();
			//Assert.assertEquals(catalogueLinks, urlList);
			// go to price page
			getDriver().findElement(By.xpath("//div[@class='links-bar']/div[@class='link']/a")).click();

			
			
//			itemNames.add(getDriver().findElement(By.xpath("//div[@class='item'][" + i + "]/div/a")).getText());
//			getDriver().findElement(By.id("edit-name-1")).sendKeys(itemNames.get(i-1));
//			getDriver().findElement(By.id("edit-submit-1")).click();
		}
		System.out.println(catalogueLinks);
		System.out.println(urlList);
	}

}
