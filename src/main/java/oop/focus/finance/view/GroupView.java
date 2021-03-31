package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import oop.focus.finance.controller.BaseController;
import oop.focus.finance.controller.FXMLPaths;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupView implements Initializable, View {

    @FXML
    private ScrollPane groupMovementsScroll;
    @FXML
    private Button peopleButton;
    @FXML
    private Button groupTransactionsButton;
    @FXML
    private Button newPersonButton;
    @FXML
    private Button resolveButton;
    @FXML
    private Button newGroupTransactionButton;

    private final BaseController controller;
    private Parent root;

    public GroupView(final BaseController controller) {
        this.controller = controller;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.GROUP.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final Node getRoot() {
        return this.root;
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.populate();
    }

    private void populate() {
        //TODO
    }
}
