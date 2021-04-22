package oop.focus.diary.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import oop.focus.application.controller.Sections;
import oop.focus.application.view.AbstractButtonsView;
import oop.focus.common.Controller;
import oop.focus.common.UpdatableController;
import oop.focus.db.DataSource;
import oop.focus.diary.controller.DiarySections;
import java.util.HashMap;
import java.util.Map;
/**
 * Extends {@link AbstractButtonsView} and creates and manages new buttons. Each of these buttons is relatives
 * to a section of diary.
 */
public class ButtonsDiaryView extends AbstractButtonsView {
    private static final Rectangle2D SCREEN_BOUNDS = Screen.getPrimary().getBounds();
    private static final Double INSETS = 0.02;
    private static final Double BUTTONS_WIDTH = 0.7;
    private static final Double VBOX_WIDTH = 0.3;
    private static final Double BUTTONS_HEIGHT = 0.1;
    private final VBox pane;
    private final Map<Node, Controller> map;
    private final Sections controller;
    private final UpdatableController<Controller> sectionsController;
    /**
     * Instantiates a new buttons view.
     * @param sectionsController    the controller of sections
     * @param dataSource    the {@link DataSource} from which to retrieve data
     */
    public ButtonsDiaryView(final UpdatableController<Controller> sectionsController, final DataSource dataSource) {
        super(sectionsController);
        this.sectionsController = sectionsController;
        this.map = new HashMap<>();
        this.pane = new VBox();
        this.controller = new DiarySections(dataSource);
        this.setButtons();
    }
    /**
     * Creates different {@link Button} which pressed show the View associated with the correspondent Controller.
     */
    private void setButtons() {
        this.controller.getList().forEach(s -> {
            final Button b = new Button(s.getValue());
            b.getStyleClass().addAll("lateral-button");
            b.setPrefHeight(this.pane.heightProperty().multiply(BUTTONS_HEIGHT).get());
            b.prefWidthProperty().bind(this.pane.widthProperty().multiply(BUTTONS_WIDTH));
            this.pane.getChildren().add(b);
            this.map.put(b, s.getKey());
        });
        this.pane.setPrefWidth(SCREEN_BOUNDS.getWidth() * VBOX_WIDTH);
        this.pane.getChildren().forEach(s -> VBox.setMargin(s, new Insets(SCREEN_BOUNDS.getHeight()
                * INSETS)));
        this.pane.setAlignment(Pos.CENTER);
        super.setOnClick(this.pane, this.map);
        this.sectionsController.updateInput(this.controller.getStarterController());
    }
}
