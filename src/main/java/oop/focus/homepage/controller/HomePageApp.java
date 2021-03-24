package oop.focus.homepage.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageApp extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final Parent root = FXMLLoader.load(getClass().getResource("calendarHomePage.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static final void main(final String... args) {
        launch(args);
    }

}
