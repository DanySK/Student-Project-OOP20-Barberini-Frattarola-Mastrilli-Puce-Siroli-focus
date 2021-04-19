package oop.focus.homepage.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.model.HotKeyType;

/**
 * This class is used to model an event hot key, wich is represented from a button.
 */
public class EventHotKeyView extends Pane implements HotKeyView {

    private final HomePageController controller;
    private final Button button;

    public EventHotKeyView(final String name, final HomePageController controller) {
        this.controller = controller;
        this.button = new Button(name);
        this.button.setStyle("-fx-background-color:" + HotKeyType.EVENT.getColor() + ";");
        this.setAction();
        this.getChildren().add(this.button);
    }

    public final Button getButton() {
        return this.button;
    }

    public final void setAction() {
        this.button.setOnAction(event -> {
            this.controller.setText(this.button.getText());
            final GenericAddView newEvent = new NewEventViewImpl(this.controller);
            final Stage stage = new Stage();
            stage.setScene(new Scene((Parent) newEvent.getRoot()));
            stage.show();
        });
    }
}
