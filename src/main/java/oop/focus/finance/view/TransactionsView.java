package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.model.Transaction;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class TransactionsView implements Initializable, View {

    @FXML
    private ScrollPane transactionsScroll;
    @FXML
    private ScrollPane accountsScroll;
    @FXML
    private Label amountLabel;
    @FXML
    private Button newAccountButton;

    private final TransactionsController controller;
    private Parent root;

    public TransactionsView(final TransactionsController controller) {
        this.controller = controller;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.ALL.getPath()));
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
        Node accountsButtons = new AccountButtonsImpl(this.controller);
        this.accountsScroll.setContent(accountsButtons);
        this.amountLabel.setText("€ " + this.controller.getAmount());
    }

    public void updateTransactions(final Set<Transaction> transactions) {
        /*
        Node transactionsBox = new TransactionsBoxImpl(transactions);
        this.transactionsScroll.setContent(transactionsBox);
        */
    }
}
