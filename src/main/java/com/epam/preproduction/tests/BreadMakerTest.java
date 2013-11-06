package com.epam.preproduction.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.epam.preproduction.configuration.DataProviderLayer;
import com.epam.preproduction.pages.CataloguePage;

/*
 * 5. Проверить корректность работы фильтра «регулировка веса» у хлебопечек, а
 * именно: проверить, что, выбрав наличие функции "регулировка веса" в фильтре,
 * результаты показываются правильно (показываются все товары, у которых есть
 * эта функция, и не показывается ничего лишнего)
 */
public class BreadMakerTest extends TestBase {

	@Test(dataProvider = "readFromExcel", dataProviderClass = DataProviderLayer.class)
	public void testBreadMaker(String categoryName, String productType)
			throws Exception {
		System.out.println("===========testBreadMaker(" + categoryName + ", "
				+ productType + ")===========");
		goToMainPage();
		driver.findElement(By.linkText(productType)).click();

		CataloguePage cataloguePage = PageFactory.initElements(driver,
				CataloguePage.class);

		cataloguePage.checkFilterWeightControlParameterForBreadMaker();
		cataloguePage.checkManufacturerInNamesForBreadMakers();
	}

}
