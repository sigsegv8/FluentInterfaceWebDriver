package com.taydavid.pages.home;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.taydavid.factory.DriverFactory;

public class HomePageController {

	@FindBy(className = "story-title")
	private List<WebElement> stories;

	@FindBy(xpath = "//*[@class=\"topic\"]/a/img")
	private List<WebElement> topics;

	@FindBy(xpath = "//*[@id=\"pollBooth\"]/label")
	private List<WebElement> pollOptions;

	@FindBy(className = "btn-polls")
	private WebElement pollButton;

	private String pollOptionName;

	public HomePageController() {
		PageFactory.initElements(DriverFactory.INSTANCE.getWebDriver(), this);
	}

	public HomePageController printHowManyArticlesOnPage() {
		System.out.println("There are " + stories.size() + " articles on the home page");
		return this;
	}

	public HomePageController printHowManyTimesEachArticleIconWasDisplayed() {
		Map<Object, Long> uniqueTopicCounts = topics.parallelStream()
				.collect(Collectors.groupingBy(icon -> icon.getAttribute("title"), Collectors.counting()));
		System.out.println("Unique article icons along with count of occurrence(s)");
		System.out.println(Arrays.toString(uniqueTopicCounts.entrySet().toArray()));
		return this;
	}

	public HomePageController clickRandomPollOption() {
		Random random = new Random();
		Assert.assertTrue(pollOptions.size() > 0, "Poll Options size must be greater than 0");
		int index = random.nextInt(pollOptions.size());
		WebElement pollOption = pollOptions.get(index);

		pollOptionName = pollOption.getText();
		System.out.println("Poll option that was randomly selected: " + pollOptionName);
		pollOption.click();
		return this;
	}

	public HomePageController clickPollSubmitButton() {
		pollButton.click();
		return this;
	}

	public String getPollOptionName() {
		return pollOptionName;
	}
}
