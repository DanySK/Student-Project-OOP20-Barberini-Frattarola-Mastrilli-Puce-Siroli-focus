package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.model.Transaction;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TransactionViewImpl extends VBox implements Initializable, TransactionView {

    @FXML
    private Label descriptionLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label amountLabel;

    private final TransactionsController controller;
    private final Transaction transaction;
    private Parent root;

    public TransactionViewImpl(final TransactionsController controller, final Transaction transaction) {
        this.transaction = transaction;
        this.controller = controller;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.MOVTILE.getPath()));
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
        this.descriptionLabel.setText(this.transaction.getDescription());
        this.categoryLabel.setText(this.transaction.getCategory().getName());
        this.amountLabel.setText("E " + (double) this.transaction.getAmount() / 100);
    }

    @Override
    public final Transaction getTransaction() {
        return this.transaction;
    }
}
