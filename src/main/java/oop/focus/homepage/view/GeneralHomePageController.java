package oop.focus.homepage.view;

import javafx.scene.Node;
import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.finance.controller.FinanceHomePageController;
import oop.focus.finance.controller.FinanceHomePageControllerImpl;
import oop.focus.finance.model.FinanceManager;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.controller.HomePageControllerImpl;

public class GeneralHomePageController implements Controller {

    private final DataSource dsi;
    private final GeneralHomePageView view;
    private final HomePageController calendarHomePage;
    private final FinanceHomePageController financheHomePage;

    public GeneralHomePageController(final DataSource dsi, final FinanceManager financeManager) {
        this.dsi = dsi;
        this.calendarHomePage = new HomePageControllerImpl(this.dsi);
        this.financheHomePage = new FinanceHomePageControllerImpl(financeManager);
        this.view = new GeneralHomePageView(this);
    }

    public final Node getCalendarHomePage() {
        return this.calendarHomePage.getView().getRoot();
    }

    public final Node getFinanaceHomePage() {
        return  this.financheHomePage.getView().getRoot();
    }

    public final View getView() {
        return this.view;
    }
}
