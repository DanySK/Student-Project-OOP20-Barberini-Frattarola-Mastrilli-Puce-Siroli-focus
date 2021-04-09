package oop.focus.finance.view.bases;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.tiles.TransactionView;
import oop.focus.finance.view.tiles.TransactionViewImpl;
import oop.focus.finance.view.windows.FinanceWindow;
import oop.focus.finance.view.windows.NewAccountViewImpl;
import oop.focus.finance.view.windows.TransactionDetailsWindowImpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class TransactionsViewImpl extends GenericView<TransactionsController> implements TransactionsView {

    @FXML
    private ScrollPane transactionsScroll, accountsScroll;
    @FXML
    private Label accountLabel, amountLabel;
    @FXML
    private Button newAccountButton, deleteButton;

    public TransactionsViewImpl(final TransactionsController controller) {
        super(controller, FXMLPaths.ALL);
    }

    @Override
    public final void populate() {
        this.newAccountButton.setText("Crea Conto");
        this.newAccountButton.setOnAction(event -> this.showNewAccount());
        this.deleteButton.setOnAction(event -> this.deleteAccounts());
        final Node accountsButtons = new AccountButtonsImpl(super.getX());
        this.accountsScroll.setContent(accountsButtons);
    }

    @Override
    public final void updateTransactions(final Set<Transaction> transactions, final Predicate<Account> predicate) {
        DecimalFormat df = new DecimalFormat("#0.00");
        this.accountLabel.setText(super.getX().getAccountName());
        this.amountLabel.setText("E " + df.format(super.getX().getAmount(predicate)));
        this.deleteButton.setText("Elimina " + super.getX().getAccountName());
        final List<TransactionView> transactionsTiles = new ArrayList<>();
        transactions.forEach(t -> transactionsTiles.add(new TransactionViewImpl(t)));
        final VBox box = new VBox();
        transactionsTiles.forEach(t -> box.getChildren().add(t.getRoot()));
        transactionsTiles.forEach(t -> t.getRoot().addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> this.showDetails(t.getTransaction())));
        this.transactionsScroll.setContent(box);
    }

    private void showDetails(final Transaction transaction) {
        final View details = new TransactionDetailsWindowImpl(super.getX(), transaction);
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) details.getRoot()));
        stage.show();
    }

    private void showNewAccount() {
        final FinanceWindow newAccount = new NewAccountViewImpl(super.getX());
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) newAccount.getRoot()));
        stage.show();
    }

    private void deleteAccounts() {
        var result = super.confirm("Sicuro di voler eliminare " + super.getX().getAccountName() + "?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            super.getX().deleteAccounts();
        }
    }
}
