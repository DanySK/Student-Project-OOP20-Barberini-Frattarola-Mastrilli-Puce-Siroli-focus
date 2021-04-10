package oop.focus.calendar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.calendar.controller.CalendarController;
import oop.focus.calendar.controller.CalendarControllerImpl;




public class CalendarPageViewTest extends Application {

    private static final double WIDTH = 700;
    private static final double HEIGHT = 500;

    public final void start(final Stage primaryStage) {

        final CalendarController pageview = new CalendarControllerImpl(300, 150, 200, 500);
        primaryStage.setScene(new Scene(pageview.getCalendarPage(), WIDTH, HEIGHT));
        primaryStage.show();
    }

    public static final void main(final String... args) {
        launch(args);
    }
}

