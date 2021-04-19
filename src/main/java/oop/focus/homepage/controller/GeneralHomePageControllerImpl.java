package oop.focus.homepage.controller;

import javafx.scene.Node;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.diary.view.ContainerFactory;
import oop.focus.diary.view.ContainerFactoryImpl;
import oop.focus.finance.controller.FinanceHomePageController;
import oop.focus.finance.controller.FinanceHomePageControllerImpl;
import oop.focus.finance.model.FinanceManager;
import oop.focus.homepage.view.GeneralHomePageView;
import oop.focus.statistics.view.ViewFactoryImpl;

import java.util.List;

public class GeneralHomePageControllerImpl implements GeneralHomePageController {

    private final GeneralHomePageView view;
    private final HomePageController calendarHomePage;
    private final FinanceHomePageController financheHomePage;

    public GeneralHomePageControllerImpl(final DataSource dsi, final FinanceManager financeManager) {
        this.calendarHomePage = new HomePageControllerImpl(dsi);
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
        //return new ViewFactoryImpl().createHorizontal(List.of(this.calendarHomePage.getView(), this.financheHomePage.getView()));
        return new ContainerFactoryImpl().mergeHorizontally(List.of(this.calendarHomePage.getView().getRoot(), this.financheHomePage.getView().getRoot()));
    }

    @Override
    public final HomePageController getHomePageController() {
        return this.calendarHomePage;
    }
}
