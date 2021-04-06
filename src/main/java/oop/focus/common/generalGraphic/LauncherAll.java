package oop.focus.common.generalGraphic;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import oop.focus.diary.controller.LauncherDiary;

import java.util.ArrayList;
import java.util.List;

public class LauncherAll extends Application {
    private static final String PATH_MAIN_STYLE = "/layouts/generalGraphics/GeneralStyle.css";
    private final CommonView commonView = new CommonView(640,480);
    @Override
    public void start(Stage primaryStage) throws Exception {
        final String css = LauncherDiary.class.getResource(PATH_MAIN_STYLE).toExternalForm();
        Scene scene = new Scene(commonView.getPane());
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args){launch(args);}
}
