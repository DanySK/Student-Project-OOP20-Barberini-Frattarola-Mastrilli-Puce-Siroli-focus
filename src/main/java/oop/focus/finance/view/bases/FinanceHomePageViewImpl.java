package oop.focus.finance.view.bases;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.FinanceHomePageController;
import oop.focus.finance.controller.NewQuickTransactionControllerImpl;
import oop.focus.finance.controller.NewTransactionControllerImpl;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.tiles.GenericTileView;
import oop.focus.finance.view.tiles.GenericTileViewImpl;
import oop.focus.statistics.view.ViewFactory;

import java.util.ArrayList;
import java.util.List;

public class FinanceHomePageViewImpl extends GenericView<FinanceHomePageController> implements FinanceHomePageView {

    private static final double RATIO = 0.01;

    @FXML
    private BorderPane mainPane;
    @FXML
    private VBox accountsVBox, movementsVBox, financeHotKeyVBox, leftVBox;
    @FXML
    private HBox downHBox;
    @FXML
    private ScrollPane quickTransactionsScroll, transactionsScroll, accountsScroll;
    @FXML
    private Label amountLabel;
    @FXML
    private Button newMovememntButton, newQuickTransactionButton;

    public FinanceHomePageViewImpl(final FinanceHomePageController controller) {
        super(controller, FXMLPaths.HOMEPAGE);
    }

    @Override
    public final void populate() {
        this.populateAccounts();
        this.populateRecentTransactions();
        this.populateQuickTransactions();
        this.newMovememntButton.setOnAction(event ->
                this.show(new NewTransactionControllerImpl(super.getX().getManager()).getView()));
        this.setPref();
    }

    private void setPref() {
        this.newQuickTransactionButton.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        this.newMovememntButton.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        this.accountsScroll.prefHeightProperty().bind(this.mainPane.prefHeightProperty());
        this.quickTransactionsScroll.setPrefHeight(Screen.getPrimary().getBounds().getHeight());
        this.quickTransactionsScroll.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        this.transactionsScroll.prefHeightProperty().bind(this.mainPane.prefHeightProperty());
    }

    @Override
    public final void populateAccounts() {
        this.amountLabel.setText(this.format(super.getX().getTotalAmount()));
        this.accountsVBox.getChildren().clear();
        final List<GenericTileView<Account>> fastAccountTiles = new ArrayList<>();
        super.getX().getSortedAccounts().forEach(a -> fastAccountTiles.add(
                new GenericTileViewImpl<>(a, a.getColor(), a.getName(), "",
                        this.format(super.getX().getAmount(a)))));
        fastAccountTiles.forEach(t -> this.accountsVBox.getChildren().add(t.getRoot()));
    }

    @Override
    public final void populateRecentTransactions() {
        this.movementsVBox.getChildren().clear();
        final List<GenericTileView<Transaction>> fastTransactionTiles = new ArrayList<>();
        super.getX().getSortedTodayTransactions().forEach(t -> fastTransactionTiles.add(
                new GenericTileViewImpl<>(t, t.getCategory().getColor(), t.getDescription(),
                        t.getCategory().getName(), this.format((double) t.getAmount() / 100))));
        fastTransactionTiles.forEach(t -> this.movementsVBox.getChildren().add(t.getRoot()));
    }

    @Override
    public final void populateQuickTransactions() {
        this.financeHotKeyVBox.getChildren().clear();
        var pane = ViewFactory.verticalWithPadding(RATIO, RATIO, RATIO);
        final List<FinanceMenuButton<FinanceHomePageController>> financeHotKeyButtons = new ArrayList<>();
        super.getX().getSortedQuickTransactions().forEach(qt -> financeHotKeyButtons.add(
                new FinanceMenuButtonImpl<>(qt.getDescription(), c -> c.doQuickTransaction(qt))));
        financeHotKeyButtons.forEach(b -> pane.getChildren().add(b.getButton()));
        financeHotKeyButtons.forEach(b -> b.getButton().setPrefWidth(Screen.getPrimary().getBounds().getWidth()));
        financeHotKeyButtons.forEach(b -> b.getButton().setOnAction(event -> b.getAction(super.getX())));
        this.newQuickTransactionButton.setOnAction(event -> this.show(new NewQuickTransactionControllerImpl(super.getX().getManager()).getView()));
        this.financeHotKeyVBox.getChildren().add(pane);
    }

    private void show(final View view) {
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) view.getRoot()));
        stage.show();
    }
}
