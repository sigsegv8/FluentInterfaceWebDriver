package com.taydavid.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.taydavid.factory.DriverFactory;

public class BaseTest {

	@BeforeMethod
	@Parameters({ "browser" })
	public void startUpBrowser(@Optional("chrome") String browser) {

		DriverFactory.INSTANCE.initWebDriver(browser.replace("\"", ""));
		DriverFactory.INSTANCE.getWebDriver().get(DriverFactory.INSTANCE.getConfiguration().getHomePageURL());

		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		DriverFactory.INSTANCE.wait(pageLoadCondition);
	}

	@AfterMethod
	public void closeBrowser() {
		DriverFactory.INSTANCE.quitWebDriver();
	}
}
