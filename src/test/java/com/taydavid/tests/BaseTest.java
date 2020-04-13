package com.taydavid.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.taydavid.factory.DriverFactory;

public class BaseTest {

	@BeforeMethod
	@Parameters({ "browser" })
	public void startUpBrowser(String browser) {

		DriverFactory.INSTANCE.initWebDriver(browser);
		DriverFactory.INSTANCE.getWebDriver().get(DriverFactory.INSTANCE.getConfiguration().getHomePageURL());

		DriverFactory.INSTANCE.jsDocumentStateReady();
	}

	@AfterMethod
	public void closeBrowser() {
		DriverFactory.INSTANCE.quitWebDriver();
	}
}
