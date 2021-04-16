package oop.focus.diary.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenWindow {
    public static void openWindow(final Parent root) {
        final Scene scene = new Scene(root);
        final Stage window = new Stage();
        window.setScene(scene);
        window.show();
    }
}
