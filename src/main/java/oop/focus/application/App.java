package oop.focus.application;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.awt.Dimension;
import java.awt.Toolkit;


public class App extends Application {
    private static final String PATH_MAIN_STYLE = "/styles/GeneralStyle.css";
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final double width = this.screenSize.getWidth() * 0.7;
    private final double height = this.screenSize.getHeight() * 0.7;
    private final GeneralController controller = new GeneralController();
    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final String css = App.class.getResource(PATH_MAIN_STYLE).toExternalForm();
        final Scene scene = new Scene((Parent) this.controller.getView().getRoot());
        scene.getStylesheets().add(css);
        scene.getStylesheets().add(Style.STATISTIC_STYLE.getPath());
        primaryStage.setHeight(height);
        primaryStage.setWidth(width);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(final String[] args) {
        launch(args);
    }
}
