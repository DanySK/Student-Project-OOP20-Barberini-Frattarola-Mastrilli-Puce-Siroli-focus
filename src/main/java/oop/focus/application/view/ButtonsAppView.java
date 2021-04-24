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
import oop.focus.common.Controller;
import oop.focus.common.UpdatableController;

import java.util.HashMap;
import java.util.Map;

/**
 * Extends {@link ButtonsView} and creates and manages new buttons. Each of these buttons is relatives
 * to a section of application.
 */
public class ButtonsAppView extends ButtonsView {
    private static final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private static final Double INSETS = 0.01;
    private static final Double BOX_HEIGHT = 0.2;
    private static final Double BUTTONS_HEIGHT = 0.6;
    private static final Double BUTTONS_WIDTH = 0.25;
    private final Pane pane;
    private final Map<Node, Controller> map;
    private final Sections controller;
    private final UpdatableController<Controller> sectionsController;
    public ButtonsAppView(final UpdatableController<Controller> sectionsController) {
        super(sectionsController);
        this.sectionsController = sectionsController;
        this.controller = new SectionsImpl();
        this.pane = new HBox();
        this.map = new HashMap<>();
        this.setButtons();
    }

    /**
     * Creates different {@link Button} which pressed show the View associated with the correspondent Controller.
     */
    private void setButtons() {
        this.pane.prefHeightProperty().set(SCREEN_BOUNDS.getHeight() * BOX_HEIGHT);

        this.controller.getList().forEach(s -> {
            final Button b = new Button(s.getValue());
            b.getStyleClass().addAll("upper-button");
            b.prefHeightProperty().bind(this.pane.heightProperty().
                    multiply(BUTTONS_HEIGHT));
            b.setPrefWidth(SCREEN_BOUNDS.getWidth() * BUTTONS_WIDTH);
            this.pane.getChildren().add(b);
            this.map.put(b, s.getKey());
        });
        this.pane.getChildren().forEach(s -> HBox.setMargin(s,
                new Insets(SCREEN_BOUNDS.getWidth() * INSETS)));
        super.setOnClick(this.pane, this.map);
        this.sectionsController.updateInput(this.controller.getStarterController());
    }
}
