package com.taydavid.tests.integration;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.taydavid.pages.home.HomePage;
import com.taydavid.pages.poll.PollResultsPage;
import com.taydavid.tests.BaseTest;

public class IntegrationTest extends BaseTest {

	private HomePage homePage;
	private PollResultsPage pollResultsPage;

	@BeforeMethod
	public void beforeMethod() {
		homePage = HomePage.getHomePage();
		pollResultsPage = PollResultsPage.getPollResultsPage();
	}

	@Test
	public void testTypicalUserPath() throws InterruptedException {
		final String selectedPollOption = homePage.action().printHowManyArticlesOnPage()
				.printHowManyTimesEachArticleIconWasDisplayed().clickRandomPollOption().clickPollSubmitButton()
				.getPollOptionName();

		pollResultsPage.action().printPollVotedResults(selectedPollOption);
	}
}
