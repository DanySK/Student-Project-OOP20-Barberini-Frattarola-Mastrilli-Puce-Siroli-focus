package oop.focus.homepage.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageApp extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
       //final Parent root = FXMLLoader.load(getClass().getResource("/layouts/homepage/calendarHomePage.fxml"));
        final HomePageController controller = new HomePageController();
        final HomePageBaseView home = new HomePageBaseView(controller);

        primaryStage.setScene(new Scene(home.getRoot()));
        primaryStage.show();
    }

    public static final void main(final String... args) {
        launch(args);
    }

}
