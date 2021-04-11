package oop.focus.homepage.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.view.HomePageBaseView;
import oop.focus.homepage.view.HomePageBaseViewImpl;

public class HomePageLauncher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {

        final DataSource dsi = new DataSourceImpl();
        final HomePageController controller = new HomePageControllerImpl(dsi);
        final HomePageBaseView home = new HomePageBaseViewImpl(controller);
        primaryStage.setScene(new Scene((Parent) home.getRoot()));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());

    }

    public static final void main(final String... args) {
        launch(args);
    }

}
