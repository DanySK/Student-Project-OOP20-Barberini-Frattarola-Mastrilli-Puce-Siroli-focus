package oop.focus.application.view;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
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
 * Extends {@link AbstractButtonsView} and creates and manages new buttons. Each of these buttons is relatives
 * to a section of application.
 */
public class ButtonsAppView extends AbstractButtonsView {
    private static final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private static final Double INSETS = 0.01;
    private static final Double BOX_HEIGHT = 0.2;
    private static final Double BUTTONS_HEIGHT = 0.6;
    private final Pane pane;
    private final Map<Button, Controller> map;
    private final Sections controller;
    public ButtonsAppView(final UpdatableController<Controller> sectionsController) {
        super(sectionsController);
        this.controller = new SectionsImpl();
        this.pane = new HBox();
        this.map = new HashMap<>();
        this.setButtons();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setButtons() {
        this.controller.getList().forEach(s -> {
            final Button b = new Button(s.getValue());
            b.getStyleClass().addAll("upper-button");
            this.pane.getChildren().add(b);
            this.map.put(b, s.getKey());
        });
        this.pane.getChildren().forEach(s -> HBox.setMargin(s,
                new Insets(SCREEN_BOUNDS.getWidth() * INSETS)));
        this.pane.prefHeightProperty().set(SCREEN_BOUNDS.getHeight() * BOX_HEIGHT);
        this.map.keySet().forEach(s -> s.prefHeightProperty().bind(this.pane.heightProperty().
                multiply(BUTTONS_HEIGHT)));
        this.map.keySet().forEach(s -> s.setPrefWidth(SCREEN_BOUNDS.getWidth() / this.map.keySet().size()));
        super.setOnClick(this.pane, this.map);
        super.setFirstWindow(this.map, this.controller);
    }
}
