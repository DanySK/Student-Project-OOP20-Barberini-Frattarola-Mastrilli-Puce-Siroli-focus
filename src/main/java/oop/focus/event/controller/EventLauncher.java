package oop.focus.event.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.calendar.model.CalendarType;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.month.controller.CalendarMonthControllerImpl;
import oop.focus.calendar.week.controller.WeekController;
import oop.focus.calendar.week.controller.WeekControllerImpl;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.calendarhomepage.controller.HomePageController;
import oop.focus.calendarhomepage.controller.HomePageControllerImpl;

public class EventLauncher extends Application {

    @Override
    public final void start(final Stage primaryStage) {

        final DataSource dsi = new DataSourceImpl();
        final HomePageController homePage = new HomePageControllerImpl(dsi);
        final WeekController week = new WeekControllerImpl(dsi, homePage);
        final CalendarMonthController month = new CalendarMonthControllerImpl(CalendarType.NORMAL, dsi);
        final EventMenuController controller = new EventMenuControllerImpl(dsi, week, month);
        final Scene scene = new Scene((Parent) controller.getView().getRoot());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());

    }

    public static void main(final String... args) {
        launch(args);
    }
}
