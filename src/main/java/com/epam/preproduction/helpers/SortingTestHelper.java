package com.epam.preproduction.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.epam.preproduction.components.CompareBlock;
import com.epam.preproduction.components.FiltersBlock;
import com.epam.preproduction.entities.BreadMaker;
import com.epam.preproduction.entities.Item;
import com.epam.preproduction.entities.Microwave;
import com.epam.preproduction.pages.CataloguePage;
import com.epam.preproduction.pages.ComparePage;
import com.epam.preproduction.pages.ItemPage;
import com.epam.preproduction.pages.MainPage;

public class SortingTestHelper {

	public void verifySortingItemsByPrices(CataloguePage cataloguePage) {
		cataloguePage.getSortLineBlock().getSortPrice().click();

		List<Item> data = new ArrayList<Item>();
		int pageCount = 0;
		while (true && pageCount++ < 3) {
			data.addAll(grabItems(cataloguePage));
			if (hasNext(cataloguePage)) {
				next(cataloguePage);
			} else {
				break;
			}
		}
		System.out.println(data);

		int prevPrice = 0;
		for (Item refrigirator : data) {
			if (prevPrice > refrigirator.getPrice()) {
				Assert.fail();
			}
			prevPrice = refrigirator.getPrice();
		}
	}

	public void verifySortingItemsByNames(CataloguePage cataloguePage) {
		cataloguePage.getSortLineBlock().getSortName().click();
		List<Item> data = new ArrayList<Item>();
		int pageCount = 0;
		while (true && pageCount++ < 3) {
			data.addAll(grabItems(cataloguePage));
			if (hasNext(cataloguePage)) {
				next(cataloguePage);
			} else {
				break;
			}
		}
		System.out.println(data);
		List<Item> namesNew = new ArrayList<Item>(data);
		Collections.sort(namesNew);
		Assert.assertEquals(data, namesNew);

	}

	public List<Item> grabItems(CataloguePage cataloguePage) {
		List<Item> result = new ArrayList<Item>();
		List<WebElement> items = cataloguePage.getMainBlock().getDivClassItem();

		for (WebElement item : items) {
			BreadMaker breadMaker = new BreadMaker();
			String name = cataloguePage.getMainBlock().getDivClassName()
					.getText();
			int price = extratNumbers(cataloguePage.getMainBlock()
					.getPriceStrong().getText());

			String description = item.findElement(
					By.xpath(cataloguePage.getMainBlock().CLASS_DESCRIPTION))
					.getText();
			breadMaker.setName(name);
			breadMaker.setPrice(price);
			breadMaker.setDescription(description);
			result.add(breadMaker);
		}
		return result;
	}

	private Integer extratNumbers(String price) {
		Integer pr = Integer.valueOf(price.substring(0, price.length() - 4)
				.replace(" ", ""));
		return pr;
	}

	private boolean hasNext(CataloguePage cataloguePage) {
		WebElement element = cataloguePage.getMainBlock().getNextPage();
		if (!CollectionUtils.isEmpty(element.findElements(By
				.className(cataloguePage.getMainBlock().ACTIVE_NEXT_LINK)))) {
			return true;
		}
		return false;
	}

	private void next(CataloguePage cataloguePage) {
		WebElement element = cataloguePage.getMainBlock().getNextPage();
		if (!CollectionUtils.isEmpty(element.findElements(By
				.className(cataloguePage.getMainBlock().ACTIVE_NEXT_LINK)))) {
			element.findElement(
					By.className(cataloguePage.getMainBlock().ACTIVE_NEXT_LINK))
					.click();
		}
	}

	public void checkFilterWeightControlParameterForBreadMaker(
			CataloguePage cataloguePage) {
		// get bread makers by filter

		// getDriver().findElement(By.xpath(FiltersBlock.getFunction(searchParameter))).click();

		cataloguePage.getFilterBlock().getFunction(
				FiltersBlock.SEARCH_PARAMETER);

		List<Item> filteredBreadMakersList = new ArrayList<Item>();
		while (true) {
			filteredBreadMakersList.addAll(grabItems(cataloguePage));
			if (hasNext(cataloguePage)) {
				next(cataloguePage);
			} else {
				break;
			}
		}
		System.out.println(filteredBreadMakersList);

		// check if filtered bread makers contains required filter
		for (Item breadMaker : filteredBreadMakersList) {
			if (!breadMaker.getDescription().contains(
					FiltersBlock.SEARCH_PARAMETER)) {
				Assert.fail();
			}
		}
	}

	public void checkManufacturerInNamesForBreadMakers(
			CataloguePage cataloguePage) {
		List<Item> allBreadMakersList = new ArrayList<Item>();
		while (true) {
			allBreadMakersList.addAll(grabItems(cataloguePage));
			if (hasNext(cataloguePage)) {
				next(cataloguePage);
			} else {
				break;
			}
		}
		System.out.println(allBreadMakersList);

		List<String> manufacturerNames = getAllManufacturesFromFilter();
		boolean exist = false;
		for (Item breadMaker : allBreadMakersList) {
			for (String manufacturer : manufacturerNames) {
				if (breadMaker.getName().toLowerCase()
						.contains(manufacturer.toLowerCase())) {
					exist = true;
					break;
				}
			}
			if (!exist) {
				Assert.fail();
			} else {
				exist = false;
			}
		}
	}

	public void checkPriceFilterForWashingMachines(double minPrice,
			double maxPrice) {
		// int maxPrice = 7000;
		// int minPrice = 6000;

		List<Item> allMachines = new ArrayList<Item>();
		while (true) {
			allMachines.addAll(grabItems());
			if (hasNext()) {
				next();
			} else {
				break;
			}
		}
		System.out.println(allMachines.size());
		System.out.println(allMachines);
		getDriver().findElement(
				By.xpath(FiltersBlock.getMaxPrice((int) maxPrice))).click();
		getDriver().findElement(
				By.xpath(FiltersBlock.getMinPrice((int) minPrice))).click();
		refreshLocators();
		List<Item> allFitredMachines = new ArrayList<Item>();
		while (true) {
			allFitredMachines.addAll(grabItems());
			if (hasNext()) {
				next();
			} else {
				break;
			}
		}
		System.out.println(allFitredMachines.size());
		System.out.println(allFitredMachines);

		for (Item machine : allFitredMachines) {
			System.out.println("====>" + machine + "(" + machine.hashCode()
					+ ")");
			if (machine.getPrice() > maxPrice || machine.getPrice() < minPrice) {
				Assert.fail();
			}
		}

		String totalFilteredItems = getDriver().findElement(
				By.xpath(FiltersBlock.FILTERED_ITEMS_COUNT)).getText();

		for (Item machine : allMachines) {
			if (machine.getPrice() <= maxPrice
					&& machine.getPrice() >= minPrice) {
				// if (!allFitredMachines.contains(machine)) {
				// Assert.fail();
				// }

				Assert.assertTrue(allFitredMachines.size() == Integer
						.parseInt(totalFilteredItems));

			}
		}

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

	private List<String> getAllManufacturesFromFilter() {
		List<String> result = new ArrayList<String>();
		List<WebElement> manufactures = getDriver()
				.findElements(
						By.xpath("//*[@id='page-content-wrap']/div[3]/div[1]/div[1]/div/div[2]/div[5]/div[2]/a[@class=' active']"));
		System.out.println(manufactures);
		for (WebElement manufacture : manufactures) {
			result.add(manufacture.getText());
		}

		getDriver()
				.findElement(
						By.xpath("//*[@id='page-content-wrap']/div[3]/div[1]/div[1]/div/div[2]/div[5]/div[2]/a[17]"))
				.click();
		List<WebElement> hiddenManufactures = getDriver()
				.findElements(
						By.xpath("//*[@id='page-content-wrap']/div[3]/div[1]/div[1]/div/div[2]/div[5]/div[2]/div[3]/a[@class=' active']"));
		System.out.println(hiddenManufactures);
		for (WebElement manufacture : hiddenManufactures) {
			result.add(manufacture.getText());
		}
		return result;
	}

}
