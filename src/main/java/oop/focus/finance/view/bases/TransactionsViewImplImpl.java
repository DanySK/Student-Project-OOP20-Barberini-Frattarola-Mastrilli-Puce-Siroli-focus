package oop.focus.finance.view.bases;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
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
import oop.focus.finance.view.StaticAlerts;
import oop.focus.finance.view.StaticFormats;
import oop.focus.finance.view.tiles.TransactionView;
import oop.focus.finance.view.tiles.TransactionViewImplImpl;
import oop.focus.finance.view.windows.TransactionDetailsWindowImpl;
import oop.focus.statistics.view.ViewFactory;
import oop.focus.statistics.view.ViewFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class that implements the view of transactions and accounts.
 */
public class TransactionsViewImplImpl extends FinanceViewImpl implements TransactionsView {

    private static final double LEFT_RATIO = 0.072;

    @FXML
    private BorderPane mainPane;
    @FXML
    private ScrollPane accountsScroll, transactionsScroll;
    @FXML
    private VBox leftVBox;
    @FXML
    private Label accountLabel, amountLabel, colorLabel, currencyLabel, minusLabel;
    @FXML
    private Button newAccountButton, deleteButton, newTransactionButton;

    private final TransactionsController controller;

    public TransactionsViewImplImpl(final TransactionsController controller) {
        this.controller = controller;
        this.loadFXML(FXMLPaths.ALL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populate() {
        this.newAccountButton.setOnAction(event -> this.showWindow(new NewAccountControllerImpl(
                this.controller.getManager()).getView()));
        this.newTransactionButton.setOnAction(event -> this.showWindow(new NewTransactionControllerImpl(
                this.controller.getManager()).getView()));
        this.deleteButton.setOnAction(event -> this.deleteAccounts());
        final Node accountsButtons = new AccountButtonsImpl(this.controller).getRoot();
        this.accountsScroll.setContent(accountsButtons);
        this.setPref();
    }

    /**
     * Method that takes care of the resize of the view.
     */
    private void setPref() {
        final Rectangle2D screen = Screen.getPrimary().getBounds();
        this.colorLabel.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.accountLabel.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.currencyLabel.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.amountLabel.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.newAccountButton.setPrefWidth(screen.getWidth());
        this.deleteButton.setPrefWidth(screen.getWidth());
        this.newTransactionButton.setPrefWidth(screen.getWidth());
        this.leftVBox.setPrefWidth(screen.getWidth() * LEFT_RATIO);
        this.accountsScroll.setPrefHeight(screen.getHeight());
        this.transactionsScroll.setFitToWidth(true);
    }

    /**
     * {@inheritDoc}
     */
    public final void updateTransactions(final List<Transaction> transactions, final Predicate<Account> predicate) {
        final ViewFactory viewFactory = new ViewFactoryImpl();
        this.accountLabel.setText(this.controller.getAccountName());
        this.amountLabel.setText(StaticFormats.formatAmount(Math.abs(this.controller.getAmount(predicate))));
        this.amountLabel.setTextFill(this.controller.getAmount(predicate) > 0 ? Color.GREEN : Color.RED);
        this.minusLabel.setVisible(this.controller.getAmount(predicate) < 0);
        this.colorLabel.setTextFill(Color.valueOf(this.controller.getColor(predicate)));
        this.deleteButton.setText("Elimina " + this.controller.getAccountName());
        final List<TransactionView> transactionsTiles = new ArrayList<>();
        transactions.forEach(t -> transactionsTiles.add(new TransactionViewImplImpl(t)));
        transactionsTiles.forEach(t -> t.getRoot().addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> this.showWindow(new TransactionDetailsWindowImpl(this.controller, t.getTransaction()))));
        final View vbox = viewFactory.createVerticalAutoResizingWithNodes(transactionsTiles.stream()
                .map(View::getRoot).collect(Collectors.toList()));
        this.transactionsScroll.setContent(vbox.getRoot());
    }

    /**
     * Method that displays a window on the screen.
     *
     * @param view to be shown
     */
    private void showWindow(final View view) {
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) view.getRoot()));
        stage.show();
    }

    /**
     * Method that after confirmation deletes the referring account(s).
     */
    private void deleteAccounts() {
        final Optional<ButtonType> result = StaticAlerts.confirm("Sicuro di voler eliminare " + this.controller.getAccountName() + "?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            this.controller.deleteAccounts();
        }
    }
}
