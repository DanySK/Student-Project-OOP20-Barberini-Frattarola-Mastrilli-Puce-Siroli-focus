package oop.focus.homepage.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.view.HomePageBaseView;

public class HomePageLauncher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {

        final DataSourceImpl dsi = new DataSourceImpl();
        final HomePageController controller = new HomePageController(dsi);
        final HomePageBaseView home = new HomePageBaseView(controller);
        primaryStage.setScene(new Scene(home.getRoot()));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());

    }

    public static final void main(final String... args) {
        launch(args);
    }

}
