package com.taydavid.factory;

import java.io.InputStream;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

	private ThreadLocal<RemoteWebDriver> mWebDriver = new ThreadLocal<RemoteWebDriver>();
	private Configuration mConfiguration;

	/**
	 * ChromeWebDriver
	 * 
	 * @param browserName
	 * @return
	 */
	public void initWebDriver(final String browserName) {

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
			mWebDriver.set(new ChromeDriver(options));
		} else {
			throw new UnsupportedOperationException(
					"This test suite is not yet completed to handle this specified browser yet.");
		}
	}

	public RemoteWebDriver getWebDriver() {
		return mWebDriver.get();
	}

	public void quitWebDriver() {
		mWebDriver.get().quit();
	}

	public Configuration getConfiguration() {
		if (mConfiguration == null) {
			Yaml yaml = new Yaml(new Constructor(Configuration.class));
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.yaml");
			mConfiguration = yaml.load(inputStream);
		}
		return mConfiguration;
	}

	public void wait(final ExpectedCondition<?> condition) {
		new WebDriverWait(this.getWebDriver(), 20).until(condition);
	}
}
