package oop.focus.calendar.persons.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;

public class PersonLauncher extends Application {

    @Override
    public final void start(final Stage primaryStage) {

        final DataSource dsi = new DataSourceImpl();
        final PersonsController controller = new PersonsControllerImpl(dsi);
        primaryStage.setScene(new Scene((Parent) controller.getView().getRoot()));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }

    public static void main(final String... args) {
        launch(args);
    }
}
