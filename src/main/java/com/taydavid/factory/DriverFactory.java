package com.taydavid.factory;

import java.io.InputStream;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.taydavid.config.Configuration;

/**
 * DriverFactory Singleton instance to retrieve web driver fundamentals
 * 
 * @author tayda
 *
 */
public enum DriverFactory {
	INSTANCE;

	private RemoteWebDriver mWebDriver;
	private Configuration mConfiguration;
	private WebDriverWait mWebDriverWait;

	/**
	 * ChromeWebDriver version 80
	 * 
	 * @param browserName
	 * @return
	 */
	public RemoteWebDriver initWebDriver(final String browserName) {

		if (browserName.equalsIgnoreCase(BrowserType.CHROME)) {
			System.setProperty("webdriver.chrome.driver", this.getConfiguration().getChromeDriverPath());
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("enable-automation");
			options.addArguments("--headless");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-browser-side-navigation");
			options.addArguments("--disable-gpu");
			mWebDriver = new ChromeDriver(options);
		} else {
			throw new UnsupportedOperationException(
					"This test suite is not yet completed to handle this specified browser yet.");
		}
		return mWebDriver;
	}

	public WebDriverWait getWebDriverWait() {
		if (mWebDriverWait == null) {
			mWebDriverWait = new WebDriverWait(mWebDriver, 20);
		}
		return mWebDriverWait;
	}

	public RemoteWebDriver getWebDriver() {
		if (mWebDriver == null) {
			throw new IllegalStateException("Need to call initWebDriver() first");
		}
		return mWebDriver;
	}

	public Configuration getConfiguration() {
		if (mConfiguration == null) {
			Yaml yaml = new Yaml(new Constructor(Configuration.class));
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.yaml");
			mConfiguration = yaml.load(inputStream);
		}
		return mConfiguration;
	}
}
