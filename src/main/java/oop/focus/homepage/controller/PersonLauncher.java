package oop.focus.homepage.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.view.HomePageBaseView;
import oop.focus.homepage.view.PersonsView;

public class PersonLauncher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {

        final DataSourceImpl dsi = new DataSourceImpl();
        final PersonsController controller = new PersonsController(dsi);
        final PersonsView persons = new PersonsView(controller);
        primaryStage.setScene(new Scene(persons.getRoot()));
        primaryStage.show();
    }

    public static final void main(final String... args) {
        launch(args);
    }
}
