package oop.focus.homepage.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.homepage.view.HomePageBaseView;
import oop.focus.homepage.view.WeekView;

public class HomePageLauncher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {

        final HomePageController controller = new HomePageController();
        final HomePageBaseView home = new HomePageBaseView(controller);
        /*final WeekController controller = new WeekController();
        final WeekView week = new WeekView(controller);*/
        primaryStage.setScene(new Scene(home.getRoot()));
        primaryStage.show();
    }

    public static final void main(final String... args) {
        launch(args);
    }

}
