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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.NewAccountControllerImpl;
import oop.focus.finance.controller.NewTransactionControllerImpl;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.tiles.TransactionView;
import oop.focus.finance.view.tiles.TransactionViewImpl;
import oop.focus.finance.view.windows.TransactionDetailsWindowImpl;
import oop.focus.statistics.view.ViewFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TransactionsViewImpl extends GenericView<TransactionsController> implements TransactionsView {

    private static final double LEFT_RATIO = 0.075;

    @FXML
    private BorderPane mainPane;
    @FXML
    private ScrollPane accountsScroll;
    @FXML
    private VBox transactionsVBox, leftVBox;
    @FXML
    private Label accountLabel, amountLabel, colorLabel, currencyLabel;
    @FXML
    private Button newAccountButton, deleteButton, newTransactionButton;

    public TransactionsViewImpl(final TransactionsController controller) {
        super(controller, FXMLPaths.ALL);
    }

    @Override
    public final void populate() {
        this.newAccountButton.setOnAction(event -> this.showWindow(new NewAccountControllerImpl(
                super.getX().getManager()).getView()));
        this.deleteButton.setOnAction(event -> this.deleteAccounts());
        this.newTransactionButton.setOnAction(event -> this.showWindow(new NewTransactionControllerImpl(
                super.getX().getManager()).getView()));
        final Node accountsButtons = new AccountButtonsImpl(super.getX()).getRoot();
        this.accountsScroll.setContent(accountsButtons);
        this.setPref();
    }

    private void setPref() {
        this.colorLabel.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.accountLabel.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.currencyLabel.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.amountLabel.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.newAccountButton.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        this.deleteButton.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        this.newTransactionButton.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        this.leftVBox.setPrefWidth(Screen.getPrimary().getBounds().getWidth() * LEFT_RATIO);
        this.accountsScroll.setPrefHeight(Screen.getPrimary().getBounds().getHeight());
        this.transactionsVBox.prefWidthProperty().bind(this.mainPane.prefWidthProperty().multiply(0.5));
    }

    @Override
    public final void updateTransactions(final List<Transaction> transactions, final Predicate<Account> predicate) {
        var viewFactory = new ViewFactoryImpl();
        this.accountLabel.setText(super.getX().getAccountName());
        this.amountLabel.setText(this.format(super.getX().getAmount(predicate)));
        this.colorLabel.setTextFill(Color.valueOf(super.getX().getColor(predicate)));
        this.deleteButton.setText("Elimina " + super.getX().getAccountName());
        this.transactionsVBox.getChildren().clear();
        final List<TransactionView> transactionsTiles = new ArrayList<>();
        transactions.forEach(t -> transactionsTiles.add(new TransactionViewImpl(t)));
        transactionsTiles.forEach(t -> t.getRoot().addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> this.showWindow(new TransactionDetailsWindowImpl(super.getX(), t.getTransaction()))));
        transactionsTiles.forEach(t -> this.transactionsVBox.getChildren().add(viewFactory.createVerticalAutoResizing(List.of(t)).getRoot()));
    }

    private void showWindow(final View view) {
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) view.getRoot()));
        stage.show();
    }

    private void deleteAccounts() {
        var result = super.confirm("Sicuro di voler eliminare " + super.getX().getAccountName() + "?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            super.getX().deleteAccounts();
        }
    }
}
