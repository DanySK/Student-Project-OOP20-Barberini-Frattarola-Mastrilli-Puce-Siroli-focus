package oop.focus.application.view;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import oop.focus.application.controller.Sections;
import oop.focus.application.controller.SectionsImpl;
import oop.focus.application.controller.SectionsController;
import oop.focus.common.Controller;
import oop.focus.common.View;
import java.util.HashMap;
import java.util.Map;


public class ButtonsView implements View {
    private static final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private static final Double BOX_HEIGHT = 0.2;
    private static final Double BUTTONS_HEIGHT = 0.6;
    private static final Double INSETS = 0.01;
    private final Pane hBox;
    private final Map<Button, Controller> map;
    private final Sections controller;
    private final SectionsController sectionsController;
    public ButtonsView(final SectionsController sectionsController) {
        this.map = new HashMap<>();
        this.hBox = new HBox();
        this.controller = new SectionsImpl();
        this.sectionsController = sectionsController;
        this.setButtons();
    }

    /**
     * Creates different {@link Button} which pressed shows the View associated with the correspondent Controller.
     */
    private void setButtons() {
        this.controller.getList().forEach(s -> {
            final Button b = new Button(s.getValue());
            b.getStyleClass().addAll("upper-button");
            this.hBox.getChildren().add(b);
            this.map.put(b, s.getKey());
        });
        this.hBox.getChildren().forEach(s -> HBox.setMargin(s, new Insets(SCREEN_BOUNDS.getWidth() * INSETS)));
        this.hBox.prefHeightProperty().set(SCREEN_BOUNDS.getHeight() * BOX_HEIGHT);
        this.map.keySet().forEach(s -> s.prefHeightProperty().bind(this.hBox.heightProperty().multiply(BUTTONS_HEIGHT)));
        this.map.keySet().forEach(s -> s.setPrefWidth(SCREEN_BOUNDS.getWidth() / this.map.keySet().size()));
        this.hBox.getChildren().forEach(s -> s.setOnMouseClicked(event -> this.sectionsController.update(this.map.get(s))));
        this.setFirstWindow();
    }

    /**
     * Sets the first window to open when the app starts.
     */
    private void setFirstWindow() {
        this.sectionsController.update(this.map.get(this.map.keySet().stream().filter(s -> this.map.get(s).
                equals(this.controller.getFirstWindow())).findAny().get()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Node getRoot() {
        return this.hBox;
    }
}
