package oop.focus.finance.view.bases;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import oop.focus.common.View;
import oop.focus.finance.controller.ChangeViewController;
import oop.focus.finance.controller.FXMLPaths;

/**
 * Class that implements the finance section skeleton view.
 */
public class BaseViewImpl extends GenericView implements BaseView {

    private static final double LEFT_MENU_RATIO = 0.075;

    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private ScrollPane menuScroll;

    private final ChangeViewController controller;

    public BaseViewImpl(final ChangeViewController controller) {
        this.controller = controller;
        this.loadFXML(FXMLPaths.MAIN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void changeView(final View view) {
        this.mainBorderPane.setCenter(view.getRoot());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populate() {
        this.menuScroll.setPrefWidth(Screen.getPrimary().getBounds().getWidth() * LEFT_MENU_RATIO);
        this.menuScroll.setContent(new ButtonsBoxImpl(this.controller).getRoot());
    }
}
