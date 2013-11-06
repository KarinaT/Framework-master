package com.epam.preproduction.tests;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import com.epam.preproduction.configuration.WebDriverFactory;
import com.epam.preproduction.pages.MainPage;

public class TestBase {
	
	protected static WebDriver driver;
	public static WebDriver getDriver() {
		return driver;
	}

	protected String baseUrl;

	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public void setUp() throws Exception {
		System.out.println("==start==>setUp");
		driver = WebDriverFactory.getDriver(DesiredCapabilities.firefox());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("===end===>setUp");
	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	protected MainPage goToMainPage() throws Exception {
		driver.get(MainPage.BASE_URL + "/");
		return PageFactory.initElements(driver, MainPage.class);
	}
}
