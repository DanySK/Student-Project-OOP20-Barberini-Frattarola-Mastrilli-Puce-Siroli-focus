package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import oop.focus.db.DataSource;
import oop.focus.finance.controller.BaseController;
import oop.focus.finance.controller.FXMLPaths;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BaseView implements Initializable, View {

    @FXML
    private ScrollPane menuScroll;
    @FXML
    private Pane mainPane;

    private final BaseController controller;
    private final DataSource db;
    private Parent root;

    public BaseView(final BaseController controller, final DataSource db) {
        this.controller = controller;
        this.db = db;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.MAIN.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final Parent getRoot() {
        return this.root;
    }

    public final void changeView(final Node frame) {
        this.mainPane.getChildren().clear();
        this.mainPane.getChildren().add(frame);
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.populate();
    }

    private void populate() {
        Node buttons = new ButtonsBoxImpl(this.controller, this.db);
        this.menuScroll.setContent(buttons);
    }
}
