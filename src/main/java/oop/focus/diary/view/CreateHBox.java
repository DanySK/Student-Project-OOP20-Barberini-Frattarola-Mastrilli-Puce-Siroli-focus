package oop.focus.diary.view;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import oop.focus.common.View;

import java.util.List;

public class CreateHBox {
    Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();

    public View createVBox(List<Node> list) {
        VBox vBox = new VBox();
        //vBox.setAlignment(Pos.CENTER);
        list.forEach(a -> vBox.getChildren().addAll(a));
        vBox.getChildren().forEach(s -> VBox.setVgrow(s, Priority.ALWAYS));
    return new View() {
            @Override
            public Node getRoot() {
                return vBox;
            }
        };
    }
    public View createHBox(List<Node> list) {
        HBox hBox = new HBox();
       // hBox.setAlignment(Pos.CENTER);
        hBox.setPrefWidth(SCREEN_BOUNDS.getWidth());
        list.forEach(s -> hBox.getChildren().addAll(s));
        hBox.getChildren().forEach(s -> HBox.setHgrow(s, Priority.ALWAYS));
        return new View() {
            @Override
            public Node getRoot() {
                return hBox;
            }
        };
    }
}
