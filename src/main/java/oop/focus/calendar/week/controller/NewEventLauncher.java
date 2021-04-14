package oop.focus.calendar.week.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.calendar.model.CalendarType;
import oop.focus.calendar.month.controller.CalendarMonthController;
import oop.focus.calendar.month.controller.CalendarMonthControllerImpl;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;

public class NewEventLauncher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final DataSource dsi = new DataSourceImpl();
        final WeekController week = new WeekControllerImpl(dsi);
        final CalendarMonthController monthController = new CalendarMonthControllerImpl(CalendarType.NORMAL, dsi);
        final NewEventController controller = new NewEventControllerImpl(dsi, week, monthController);

        primaryStage.setScene(new Scene((Parent) controller.getView().getRoot()));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }

    public static final void main(final String... args) {
        launch(args);
    }

}
