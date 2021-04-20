package oop.focus.application.view;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import oop.focus.application.controller.Sections;
import oop.focus.common.Controller;
import oop.focus.common.UpdatableController;
import oop.focus.common.View;
import java.util.Map;

/**
 * The class views upper buttons of sections of app. When one of them is pressed, a specific View,
 * associated with the section,is shown. ButtonsView also sets the first window to be opened when
 * application is launched.
 */
public abstract class AbstractButtonsView implements View {
    private Pane pane;
    private final UpdatableController<Controller> sectionsController;
    public AbstractButtonsView(final UpdatableController<Controller> sectionsController) {
        this.sectionsController = sectionsController;
    }

    /**
     * Creates different {@link Button} which pressed show the View associated with the correspondent Controller.
     */
    public abstract void setButtons();

    /**
     * The method sets the action to do when a button is pressed. In that case, when the button is pressed,
     * is shown the View relatives to the Controller associated to the button.
     * @param pane  the container of buttons
     * @param map   a Map of element, each one has a {@link Button} as key and a {@link Controller} as value.
     */
    public void setOnClick(final Pane pane, final Map<Button, Controller> map) {
        this.pane = pane;
        pane.getChildren().forEach(s -> s.setOnMouseClicked(event ->
                this.sectionsController.updateInput(map.get(s))));
    }
    /**
     * Sets the first window to open when the application starts.
     * @param map    a Map of element, each one has a {@link Button} as key and a {@link Controller} as value.
     * @param controller    the Controller relatives to the first View that must be shown.
     */
    public void setFirstWindow(final Map<Button, Controller> map, final Sections controller) {
        this.sectionsController.updateInput(map.get(map.keySet().stream().filter(s -> map.get(s).
                equals(controller.getStarterController())).findAny().get()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Node getRoot() {
        return this.pane;
    }
}
