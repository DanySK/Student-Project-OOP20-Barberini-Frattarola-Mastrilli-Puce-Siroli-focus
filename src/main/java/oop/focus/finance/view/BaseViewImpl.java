package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import oop.focus.common.View;
import oop.focus.finance.controller.BaseController;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.model.FinanceManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BaseViewImpl implements Initializable, BaseView {

    @FXML
    private ScrollPane menuScroll;
    @FXML
    private Pane mainPane;

    private final BaseController controller;
    private final FinanceManager manager;
    private Parent root;

    public BaseViewImpl(final BaseController controller, final FinanceManager manager) {
        this.controller = controller;
        this.manager = manager;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.MAIN.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }

    @Override
    public final void changeView(final View view) {
        this.mainPane.getChildren().clear();
        this.mainPane.getChildren().add(view.getRoot());
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.populate();
    }

    private void populate() {
        final Node buttons = new ButtonsBoxImpl(this.controller, this.manager);
        this.menuScroll.setContent(buttons);
    }
}
