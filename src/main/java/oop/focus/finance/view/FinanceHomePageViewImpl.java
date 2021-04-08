package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.FinanceHomePageController;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Transaction;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FinanceHomePageViewImpl implements Initializable, View {

    @FXML
    private ScrollPane accountsScroll, movementsScroll, financeHotKeyScroll;
    @FXML
    private Label amountLabel, accountLabel, movementsLabel;
    @FXML
    private Button newMovememntButton;

    private final FinanceHomePageController controller;
    private Parent root;

    public FinanceHomePageViewImpl(final FinanceHomePageController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.HOMEPAGE.getPath()));
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
        this.populateFastAccounts();
        this.populateFastTransactions();
        this.populateFinanceHotKeys();
        this.newMovememntButton.setOnAction(event -> this.showNewMovement());
    }

    private void showNewMovement() {
        final FinanceWindow newTransaction = new NewTransactionViewImpl(this.controller);
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) newTransaction.getRoot()));
        stage.show();
    }

    private void populateFinanceHotKeys() {
        final List<FinanceMenuButton<FinanceHomePageController>> financeHotKeyButtons = new ArrayList<>();
        this.controller.getQuickTransactions().forEach(qt -> financeHotKeyButtons.add(
                new FinanceMenuButtonImpl<>(qt.getDescription(), c -> c.doQuickTransaction(qt))));
        final VBox box = new VBox();
        financeHotKeyButtons.forEach(b -> box.getChildren().add(b.getButton()));
        financeHotKeyButtons.forEach(b -> b.getButton().setOnAction(event -> b.getAction(this.controller)));
        this.financeHotKeyScroll.setContent(box);
    }

    private void populateFastTransactions() {
        this.movementsLabel.setText("Transazioni di oggi");
        final List<GenericTileView<Transaction>> fastTransactionTiles = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.00");
        this.controller.getTodayTransactions().forEach(t -> fastTransactionTiles.add(new GenericTileViewImpl<>(t,
                t.getDescription(), "E " + df.format((double) t.getAmount() / 100))));
        final VBox box = new VBox();
        fastTransactionTiles.forEach(t -> box.getChildren().add(t.getRoot()));
        this.movementsScroll.setContent(box);
    }

    private void populateFastAccounts() {
        this.amountLabel.setText("E " + this.controller.getTotalAmount());
        this.accountLabel.setText("Saldo totale");
        final List<GenericTileView<Account>> fastAccountTiles = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.00");
        this.controller.getAccounts().forEach(a -> fastAccountTiles.add(new GenericTileViewImpl<>(a, a.getName(),
                "E " + df.format((double) this.controller.getAmount(a) / 100))));
        final VBox box = new VBox();
        fastAccountTiles.forEach(t -> box.getChildren().add(t.getRoot()));
        this.accountsScroll.setContent(box);
    }


}
