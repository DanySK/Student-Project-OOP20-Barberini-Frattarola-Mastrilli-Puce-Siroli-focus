package oop.focus.homepage.view;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.homepage.controller.HomePageController;

public class EventHotKeyView extends Pane implements HotKeyView {

    private final HomePageController controller;
    private final Button button;

    public EventHotKeyView(final String name, final HomePageController controller) {
        this.controller = controller;
        this.button = new Button(name);
        this.setAction();
        this.getChildren().add(button);
    }

    public final Button getButton() {
        return this.button;
    }

    public final void setAction() {
        this.button.setOnAction(event -> {
            final View newEvent = new NewEventView(this.controller);
            final Stage stage = new Stage();
            stage.setScene(new Scene(newEvent.getRoot()));
            stage.show();
        });
    }
}
