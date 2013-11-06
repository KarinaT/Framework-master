package com.epam.preproduction.helpers;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.epam.preproduction.configuration.PropertyReader;

public class MyListener extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult tr) {
		String reportFolder = System.getProperty("user.dir")
				+ "/target/surefire-reports/html/";

		String screenshotFolderName = "screenshots/";
		String screenshotFullFolder = reportFolder + screenshotFolderName;
		String screenshotName = tr.getMethod() + "_" + ".png";

		String screenshotLinkFolder = screenshotFolderName + screenshotName; // For
																				// HTML
																				// report
		String screenshotPath = screenshotFullFolder + screenshotName; // For
																		// system

		PropertyReader.getScreenShotsDestinationFolder();
		Reporter.setCurrentTestResult(tr);

	}

}
