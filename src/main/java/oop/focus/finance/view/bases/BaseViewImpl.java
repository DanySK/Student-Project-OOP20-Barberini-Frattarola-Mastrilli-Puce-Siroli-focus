package oop.focus.finance.view.bases;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import oop.focus.common.View;
import oop.focus.finance.controller.BaseController;
import oop.focus.finance.controller.FXMLPaths;

public class BaseViewImpl extends GenericView<BaseController> implements BaseView {

    @FXML
    private ScrollPane menuScroll;
    @FXML
    private Pane mainPane;

    public BaseViewImpl(final BaseController controller, final FXMLPaths path) {
        super(controller, path);
    }

    @Override
    public final void changeView(final View view) {
        this.mainPane.getChildren().clear();
        this.mainPane.getChildren().add(view.getRoot());
    }

    @Override
    protected final void populate() {
        final Node buttons = new ButtonsBoxImpl(super.getX());
        this.menuScroll.setContent(buttons);
    }
}
