package oop.focus.finance.view.bases;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import oop.focus.common.View;
import oop.focus.finance.controller.BaseController;
import oop.focus.finance.controller.FXMLPaths;

public class BaseViewImpl extends GenericView<BaseController> implements BaseView {

    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private ScrollPane menuScroll;

    public BaseViewImpl(final BaseController controller) {
        super(controller, FXMLPaths.MAIN);
    }

    @Override
    public final void changeView(final View view) {
        this.mainBorderPane.setCenter(view.getRoot());
    }

    @Override
    public final void populate() {
        final Node buttons = new ButtonsBoxImpl(super.getX());
        this.menuScroll.setContent(buttons);
    }
}
