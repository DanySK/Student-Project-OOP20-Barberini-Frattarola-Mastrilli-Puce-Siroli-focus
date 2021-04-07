package oop.focus.diary.controller;

import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.diary.view.StopwatchView;

public class LauncherStopwatch extends Application {
    private static final String PATH_MAIN_STYLE = "/layouts/diary/diaryStyle.css";

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        Dimension2D dim = new Dimension2D(1400, 900);
        Scene scene = new Scene(new StopwatchView().getRoot());
        primaryStage.setScene(scene);
        final String css = LauncherDiary.class.getResource(PATH_MAIN_STYLE).toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.setWidth(dim.getWidth());
        primaryStage.setHeight(dim.getHeight());
        primaryStage.show();
    }
    public static void main(final String[] args) {
        launch(args);
    }
}
