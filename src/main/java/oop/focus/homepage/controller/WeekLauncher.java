package oop.focus.homepage.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.view.WeekView;

public class WeekLauncher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final DataSourceImpl dsi = new DataSourceImpl();
        final WeekController controller = new WeekController(dsi);
        final WeekView week = new WeekView(controller);
        primaryStage.setScene(new Scene(week.getRoot()));
        primaryStage.show();
    }

    public static final void main(final String... args) {
        launch(args);
    }

}
