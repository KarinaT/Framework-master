package com.epam.preproduction.pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class PricePage extends Page {

	public PricePage(WebDriver driver) {
		super(driver);

	}

	public void gerUrls(List<String> catalogueLinks, List<String> pricePageLinks) {
		List<String> urlList = new ArrayList<String>();
		List<String> namesList = new ArrayList<String>();

		for (int i = 1; i < 6; i++) {
			List<WebElement> names = getDriver().findElements(
					By.xpath("//div[@class='item'][" + i + "]/div/a"));
			// getting catalogue links
			for (WebElement webElement : names) {
				String hrefs = webElement.getAttribute("href");
				String itemNames = webElement.getText();
				namesList.add(itemNames);
				catalogueLinks.add(hrefs);
			}
			// visiting every item page
			getDriver().findElement(
					By.xpath("//div[@class='item'][" + i + "]/div/a")).click();
			PageFactory.initElements(getDriver(), ItemPage.class);
			urlList.add(i - 1, getDriver().getCurrentUrl());
			getDriver().navigate().back();
			refreshLocators();
			// comparing lists of links
			Assert.assertEquals(catalogueLinks, urlList);
			Assert.assertEquals(catalogueLinks, pricePageLinks,
					"Some links are shown in search results by mistake! ");

		}

		System.out.println(namesList);
		System.out.println(catalogueLinks);
		System.out.println(urlList);
		System.out.println(goToPricePage(namesList));

	}

	public Set<String> goToPricePage(List<String> namesList) {
		// go to price page
		// List<String> pricePageLinks = new ArrayList<String>();
		Set<String> pricePageLinks = new HashSet<String>();
		getDriver().findElement(
				By.xpath("//div[@class='links-bar']/div[@class='link']/a"))
				.click();

		for (int j = 0; j < namesList.size(); j++) {
			getDriver().findElement(By.id("edit-name-1")).sendKeys(
					namesList.get(j));
			getDriver().findElement(By.id("edit-submit-1")).click();

			List<WebElement> linkToDescription = getDriver().findElements(
					By.xpath("//td[@class='n']/a[1]"));

			// getting catalogue links
			for (WebElement webElement : linkToDescription) {
				String hrefs = webElement.getAttribute("href");
				pricePageLinks.add(hrefs);

			}
			getDriver().findElement(By.id("edit-name-1")).clear();

		}
		return pricePageLinks;
	}

	public void verifyItemLinksAreEqual() {
		List<String> data = new ArrayList<String>();
		List<String> pricesLinks = new ArrayList<String>();
		gerUrls(data, pricesLinks);
	}

	private void refreshLocators() {
		PageFactory.initElements(getDriver(), CataloguePage.class);
	}

}
