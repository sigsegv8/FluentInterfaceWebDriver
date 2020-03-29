package com.taydavid.pages.poll;

public class PollResultsPage {
	private PollResultsPageController action;

	/**
	 * Poll results action controller
	 * 
	 * @return
	 */
	public PollResultsPageController action() {
		return action;
	}

	private PollResultsPage() {
	}

	private PollResultsPage(PollResultsPageController action) {
		this.action = action;
	}

	public static PollResultsPage getPollResultsPage() {
		return new PollResultsPage(new PollResultsPageController());
	}
}
