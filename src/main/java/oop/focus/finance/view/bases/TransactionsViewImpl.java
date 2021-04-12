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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oop.focus.common.Controller;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.NewAccountControllerImpl;
import oop.focus.finance.controller.TransactionDetailsControllerImpl;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.tiles.TransactionView;
import oop.focus.finance.view.tiles.TransactionViewImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TransactionsViewImpl extends GenericView<TransactionsController> implements TransactionsView {

    @FXML
    private ScrollPane accountsScroll;
    @FXML
    private VBox transactionsVBox;
    @FXML
    private Label accountLabel, amountLabel, colorLabel;
    @FXML
    private Button newAccountButton, deleteButton;

    public TransactionsViewImpl(final TransactionsController controller) {
        super(controller, FXMLPaths.ALL);
    }

    @Override
    public final void populate() {
        this.newAccountButton.setOnAction(event -> this.showWindow(new NewAccountControllerImpl(super.getX().getManager())));
        this.deleteButton.setOnAction(event -> this.deleteAccounts());
        final Node accountsButtons = new AccountButtonsImpl(super.getX());
        this.accountsScroll.setContent(accountsButtons);
    }

    @Override
    public final void updateTransactions(final List<Transaction> transactions, final Predicate<Account> predicate) {
        this.accountLabel.setText(super.getX().getAccountName());
        this.amountLabel.setText(this.format(super.getX().getAmount(predicate)));
        this.colorLabel.setTextFill(Color.valueOf(super.getX().getColor(predicate)));
        this.deleteButton.setText("Elimina " + super.getX().getAccountName());
        this.transactionsVBox.getChildren().clear();
        final List<TransactionView> transactionsTiles = new ArrayList<>();
        transactions.forEach(t -> transactionsTiles.add(new TransactionViewImpl(t)));
        transactionsTiles.forEach(t -> this.transactionsVBox.getChildren().add(t.getRoot()));
        transactionsTiles.forEach(t -> t.getRoot().addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> this.showWindow(new TransactionDetailsControllerImpl(super.getX().getManager(), t.getTransaction()))));
    }

    private void showWindow(final Controller controller) {
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) controller.getView().getRoot()));
        stage.show();
    }

    private void deleteAccounts() {
        var result = super.confirm("Sicuro di voler eliminare " + super.getX().getAccountName() + "?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            super.getX().deleteAccounts();
        }
    }
}
