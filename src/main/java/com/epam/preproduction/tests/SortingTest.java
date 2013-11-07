package com.epam.preproduction.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.preproduction.configuration.DataProviderLayer;
import com.epam.preproduction.helpers.SortingTestHelper;
import com.epam.preproduction.pages.CataloguePage;

public class SortingTest extends TestBase {

	/**
	 * 1. Написать тест, проверяющий функциональность работы сортировки (по цене
	 * и названию) для раздела «Холодильники» (сортировка справа вверху).
	 * Навигация в категорию должна быть выполнена со стартовой страницы.
	 **/

	CataloguePage cataloguePage;
	SortingTestHelper sortingHelper;

	@BeforeMethod
	public void configuration() {
		cataloguePage = PageFactory.initElements(driver, CataloguePage.class);
	}

	@Test(dataProvider = "readFromExcel", dataProviderClass = DataProviderLayer.class)
	public void testSortingByPrice(String productType) throws Exception {

		goToMainPage();
		cataloguePage.selectProductType(productType);
		sortingHelper.verifySortingItemsByPrices(cataloguePage);

	}

	@Test(dataProvider = "readFromExcel", dataProviderClass = DataProviderLayer.class)
	public void testSortingByName(String productType) throws Exception {

		goToMainPage();
		cataloguePage.selectProductType(productType);
		sortingHelper.verifySortingItemsByNames(cataloguePage);
	}

}
