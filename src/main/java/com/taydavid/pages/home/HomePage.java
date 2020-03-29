package com.taydavid.pages.home;

/**
 * Home Page representation
 * 
 * @author tayda
 *
 */
public class HomePage {
	private HomePageController action;

	/**
	 * Home Page Action controller
	 * 
	 * @return
	 */
	public HomePageController action() {
		return action;
	}

	private HomePage() {
	}

	private HomePage(HomePageController action) {
		this.action = action;
	}

	public static HomePage getHomePage() {
		return new HomePage(new HomePageController());
	}
}
