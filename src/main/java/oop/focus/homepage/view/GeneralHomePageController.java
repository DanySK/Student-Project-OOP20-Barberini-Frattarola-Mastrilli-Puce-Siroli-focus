package oop.focus.homepage.view;

import javafx.scene.Node;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.finance.controller.FinanceHomePageController;
import oop.focus.finance.controller.FinanceHomePageControllerImpl;
import oop.focus.finance.model.FinanceManager;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.controller.HomePageControllerImpl;

public class GeneralHomePageController {

	private DataSource dsi;
	private GeneralHomePageView view;
	private HomePageController calendarHomePage;
	private FinanceHomePageController financheHomePage;
	
	public GeneralHomePageController(final DataSource dsi, final FinanceManager financeManager) {
		this.dsi = dsi;
		this.calendarHomePage = new HomePageControllerImpl(this.dsi);
		this.financheHomePage = new FinanceHomePageControllerImpl(financeManager);
		this.view = new GeneralHomePageView(this);
	}

	public Node getCalendarHomePage(){
		return this.calendarHomePage.getView().getRoot();
	}

	public Node getFinanaceHomePage(){
		return  this.financheHomePage.getView().getRoot();
	}

	public View getView(){
		return this.view;
	}
}
