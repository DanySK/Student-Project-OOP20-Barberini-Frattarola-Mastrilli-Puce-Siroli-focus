package oop.focus.application.view;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import oop.focus.common.Controller;
import oop.focus.common.UpdatableController;
import oop.focus.common.View;
import java.util.Map;

/**
 * This abstract class views buttons of sections of app and diary. When one of them is pressed, a specific View,
 * associated with the section,is shown. ButtonsView also sets the first window to be opened when
 * application(or diary's section) is launched.
 */
public abstract class AbstractButtonsView implements View {
    private Pane pane;
    private final UpdatableController<Controller> sectionsController;
    public AbstractButtonsView(final UpdatableController<Controller> sectionsController) {
        this.sectionsController = sectionsController;
    }

    /**
     * The method sets the action to do when a button is pressed. In that case, when the button is pressed,
     * is shown the View relatives to the Controller associated to the button.
     * @param pane  the container of buttons
     * @param map   a Map of element, each one has a {@link Node} as key and a {@link Controller} as value.
     */
    public void setOnClick(final Pane pane, final Map<Node, Controller> map) {
        this.pane = pane;
        pane.getChildren().forEach(s -> s.setOnMouseClicked(event ->
                this.sectionsController.updateInput(map.get(s))));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node getRoot() {
        return this.pane;
    }
}
