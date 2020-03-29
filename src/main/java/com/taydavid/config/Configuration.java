package com.taydavid.config;

/**
 * Configuration yaml file object model
 * 
 * @author tayda
 *
 */
public class Configuration {
	private String chromeDriverPath;
	private String homePageURL;

	public String getChromeDriverPath() {
		return chromeDriverPath;
	}

	public void setChromeDriverPath(String chromeDriverPath) {
		this.chromeDriverPath = chromeDriverPath;
	}

	public String getHomePageURL() {
		return homePageURL;
	}

	public void setHomePageURL(String homePageURL) {
		this.homePageURL = homePageURL;
	}

}
