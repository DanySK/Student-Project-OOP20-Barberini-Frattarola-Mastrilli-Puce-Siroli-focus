package oop.focus.calendar;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.calendar.controller.CalendarController;
import oop.focus.calendar.controller.CalendarControllerImpl;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.FinanceManagerImpl;
import oop.focus.homepage.controller.GeneralHomePageController;
import oop.focus.homepage.controller.GeneralHomePageControllerImpl;


public class CalendarPageViewTest extends Application {

    private static final double WIDTH = 700;
    private static final double HEIGHT = 500;

    public final void start(final Stage primaryStage) {

    	final DataSource datasource = new DataSourceImpl();
        final FinanceManager financeManager = new FinanceManagerImpl(datasource);
        final GeneralHomePageController controller = new GeneralHomePageControllerImpl(datasource, financeManager);
        final CalendarController pageController = new CalendarControllerImpl(datasource, controller);
        primaryStage.setScene(new Scene((Parent) pageController.getView().getRoot(), WIDTH, HEIGHT));
        primaryStage.show();
    }

    public static void main(final String... args) {
        launch(args);
    }
}

