package oop.focus.week.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.week.view.PersonsView;
import oop.focus.week.view.PersonsViewImpl;

public class PersonLauncher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {

        final DataSource dsi = new DataSourceImpl();
        final PersonsController controller = new PersonsControllerImpl(dsi);
        final PersonsView persons = new PersonsViewImpl(controller);
        primaryStage.setScene(new Scene((Parent) persons.getRoot()));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }

    public static final void main(final String... args) {
        launch(args);
    }
}
