package oop.focus.calendar.week.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.controller.HomePageControllerImpl;

public class WeekLauncher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final DataSource dsi = new DataSourceImpl();
        final HomePageController homePage = new HomePageControllerImpl(dsi);
        final WeekController controller = new WeekControllerImpl(dsi, homePage);
        primaryStage.setScene(new Scene((Parent) controller.getView().getRoot()));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }

    public static void main(final String... args) {
        launch(args);
    }

}
