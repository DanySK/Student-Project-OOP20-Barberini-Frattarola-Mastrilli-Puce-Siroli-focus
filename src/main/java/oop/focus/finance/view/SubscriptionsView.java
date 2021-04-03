package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.SubscriptionsController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SubscriptionsView implements Initializable, View {

    @FXML
    private ScrollPane subcriptionsScroll;
    @FXML
    private Label monthlyTransactionLlabel;
    @FXML
    private Label annualTransactionLabel;

    private final SubscriptionsController controller;
    private Parent root;

    public SubscriptionsView(final SubscriptionsController controller) {
        this.controller = controller;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.SUBS.getPath()));
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
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.populate();
    }

    private void populate() {
        //TODO
    }
}
