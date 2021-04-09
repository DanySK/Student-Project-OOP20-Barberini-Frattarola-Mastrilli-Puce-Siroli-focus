package oop.focus.finance.view.bases;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.FinanceHomePageController;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.tiles.GenericTileView;
import oop.focus.finance.view.tiles.GenericTileViewImpl;
import oop.focus.finance.view.windows.FinanceWindow;
import oop.focus.finance.view.windows.NewTransactionViewImpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FinanceHomePageViewImpl extends GenericView<FinanceHomePageController> {

    @FXML
    private ScrollPane accountsScroll, movementsScroll, financeHotKeyScroll;
    @FXML
    private Label amountLabel, accountLabel, movementsLabel;
    @FXML
    private Button newMovememntButton;

    public FinanceHomePageViewImpl(final FinanceHomePageController controller) {
        super(controller, FXMLPaths.HOMEPAGE);
    }

    @Override
    public final void populate() {
        this.populateFastAccounts();
        this.populateFastTransactions();
        this.populateFinanceHotKeys();
        this.newMovememntButton.setOnAction(event -> this.showNewMovement());
    }

    private void populateFastAccounts() {
        this.amountLabel.setText("E " + super.getX().getTotalAmount());
        this.accountLabel.setText("Saldo totale");
        final List<GenericTileView<Account>> fastAccountTiles = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#0.00");
        super.getX().getAccounts().forEach(a -> fastAccountTiles.add(new GenericTileViewImpl<>(a, a.getName(),
                "E " + df.format((double) super.getX().getAmount(a) / 100))));
        final VBox box = new VBox();
        fastAccountTiles.forEach(t -> box.getChildren().add(t.getRoot()));
        this.accountsScroll.setContent(box);
    }

    private void populateFastTransactions() {
        this.movementsLabel.setText("Transazioni di oggi");
        final List<GenericTileView<Transaction>> fastTransactionTiles = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#0.00");
        super.getX().getTodayTransactions().forEach(t -> fastTransactionTiles.add(new GenericTileViewImpl<>(t,
                t.getDescription(), "E " + df.format((double) t.getAmount() / 100))));
        final VBox box = new VBox();
        fastTransactionTiles.forEach(t -> box.getChildren().add(t.getRoot()));
        this.movementsScroll.setContent(box);
    }

    private void populateFinanceHotKeys() {
        final List<FinanceMenuButton<FinanceHomePageController>> financeHotKeyButtons = new ArrayList<>();
        super.getX().getQuickTransactions().forEach(qt -> financeHotKeyButtons.add(
                new FinanceMenuButtonImpl<>(qt.getDescription(), c -> c.doQuickTransaction(qt))));
        final VBox box = new VBox();
        financeHotKeyButtons.forEach(b -> box.getChildren().add(b.getButton()));
        financeHotKeyButtons.forEach(b -> b.getButton().setOnAction(event -> b.getAction(super.getX())));
        this.financeHotKeyScroll.setContent(box);
    }

    private void showNewMovement() {
        final FinanceWindow newTransaction = new NewTransactionViewImpl(super.getX());
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) newTransaction.getRoot()));
        stage.show();
    }
}
