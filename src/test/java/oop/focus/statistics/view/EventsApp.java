package oop.focus.statistics.view;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.common.Controller;
import oop.focus.db.DataSourceImpl;
import oop.focus.statistics.controller.EventsStatistics;

public class EventsApp extends Application {

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        final var db = new DataSourceImpl();
        final Controller controller = new EventsStatistics(db);
        primaryStage.setScene(new Scene((Parent) controller.getView().getRoot()));
        primaryStage.show();
    }
}
