package oop.focus.application.core;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.focus.application.controller.GeneralController;
import oop.focus.application.controller.Style;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This class represent the Main class of the JavaFX-based application and starts application.
 */
public class App extends Application {
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final double width = this.screenSize.getWidth() * 0.8;
    private final double height = this.screenSize.getHeight() * 0.8;
    private final GeneralController controller = new GeneralController();

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) {
        final Scene scene = new Scene((Parent) this.controller.getView().getRoot());
        Arrays.stream(Style.values()).collect(Collectors.toList()).forEach(s -> scene.getStylesheets().add(s.getPath()));
        primaryStage.setHeight(this.height);
        primaryStage.setWidth(this.width);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }

    /**
     * Main method.
     * @param args  unused.
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
