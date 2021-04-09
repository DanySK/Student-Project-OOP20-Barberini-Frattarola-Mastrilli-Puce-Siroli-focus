package oop.focus.diary.model;

import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.diary.view.BaseDiary;

public class LauncherDiary extends Application {
    private static final String PATH_MAIN_STYLE = "/layouts/diary/diaryStyle.css";
    private static final String PATH_BASE_DIARY = "/layouts/diary/baseDiary.fxml";
    private final Stage firstStage = new Stage();


    @Override
    public final void start(final Stage primaryStage) throws Exception {
        Dimension2D dim = new Dimension2D(1400, 900);
        Scene scene = new Scene((Parent) new BaseDiary().getRoot());
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
