package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Transaction;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Predicate;

public class TransactionsViewImpl implements Initializable, TransactionsView {

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

    public TransactionsViewImpl(final TransactionsController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.ALL.getPath()));
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
        //this.newAccountButton.setOnAction(event -> this.controller.newAccount());
        final Node accountsButtons = new AccountButtonsImpl(this.controller);
        this.accountsScroll.setContent(accountsButtons);
    }

    public final void updateTransactions(final Set<Transaction> transactions, final Predicate<Account> predicate) {
        this.amountLabel.setText("E " + this.controller.getAmount(predicate));
        final List<TransactionView> transactionsTiles = new ArrayList<>();
        transactions.forEach(t -> transactionsTiles.add(new TransactionViewImpl(t)));
        final VBox box = new VBox();
        transactionsTiles.forEach(t -> box.getChildren().add(t.getRoot()));
        transactionsTiles.forEach(t -> t.getRoot().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.controller.deleteTransaction(t.getTransaction())));
        this.transactionsScroll.setContent(box);
    }
}
