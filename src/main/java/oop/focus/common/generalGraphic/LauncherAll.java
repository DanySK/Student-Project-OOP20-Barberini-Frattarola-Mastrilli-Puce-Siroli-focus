package oop.focus.common.generalGraphic;

import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LauncherAll extends Application {
    private static final String PATH_MAIN_STYLE = "/layouts/generalGraphics/GeneralStyle.css";
    private final CommonView commonView = new CommonView(new Dimension2D(1000, 800));
    @Override
    public final void start(final Stage primaryStage) throws Exception {
        //final String css = LauncherDiary.class.getResource(PATH_MAIN_STYLE).toExternalForm();
        Scene scene = new Scene(this.commonView.getPane());
        //scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(final String[] args) {
        launch(args);
    }
}
