package oop.focus.homepage.view;

import oop.focus.db.DataSource;

public class GeneralHomePageController {

	private DataSource dsi;
	private GeneralHomePageView view;
	
	public GeneralHomePageController(final DataSource dsi) {
		this.dsi = dsi;
		this.view = new GeneralHomePageView(this);
	}
}
