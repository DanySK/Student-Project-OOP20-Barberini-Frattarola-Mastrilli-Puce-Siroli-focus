package oop.focus.week.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.week.view.WeekView;
import oop.focus.week.view.WeekViewImpl;

public class WeekLauncher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final DataSource dsi = new DataSourceImpl();
        final WeekController controller = new WeekControllerImpl(dsi);
        final WeekView week = new WeekViewImpl(controller);
        primaryStage.setScene(new Scene((Parent) week.getRoot()));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }

    public static final void main(final String... args) {
        launch(args);
    }

}
