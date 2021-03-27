package oop.focus.diary.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LauncherDiary extends Application {
    private static final String PATH_MAIN_STYLE = "/layouts/diary/diaryStyle.css";
    private static final String PATH_BASE_DIARY = "/layouts/diary/baseDiary.fxml";
    private final Stage firstStage = new Stage();
    @Override
    public final void start(final Stage primaryStage) throws Exception {
    final Parent root = FXMLLoader.load(this.getClass().getResource(PATH_BASE_DIARY));
    final Scene scene = new Scene(root);
    final String css = LauncherDiary.class.getResource(PATH_MAIN_STYLE).toExternalForm();
    scene.getStylesheets().add(css);
    primaryStage.setScene(scene);
    this.firstStage.setResizable(true);
    primaryStage.show();
    }
    public static void main(final String[] args) {
        launch(args);
    }
}
