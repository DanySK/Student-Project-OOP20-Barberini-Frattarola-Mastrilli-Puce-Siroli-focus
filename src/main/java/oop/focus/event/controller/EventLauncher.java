package oop.focus.event.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;

public class EventLauncher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {

        final DataSource dsi = new DataSourceImpl();
        final EventMenuController controller = new EventMenuControllerImpl(dsi);
        final Scene scene = new Scene((Parent) controller.getView().getRoot());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());

    }

    public static final void main(final String... args) {
        launch(args);
    }
}
