package oop.focus.finance.view.bases;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import oop.focus.statistics.view.ViewFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinanceHomePageViewImpl extends GenericView<FinanceHomePageController> implements FinanceHomePageView {

    private static final double RATIO = 0.01;

    @FXML
    private BorderPane mainPane;
    @FXML
    private VBox leftVBox;
    @FXML
    private HBox downHBox;
    @FXML
    private ScrollPane quickTransactionsScroll, transactionsScroll, accountsScroll;
    @FXML
    private Label amountLabel;
    @FXML
    private Button newMovememntButton, newQuickTransactionButton, deleteButton;

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
        this.newQuickTransactionButton.setOnAction(event ->
                this.show(new NewQuickTransactionControllerImpl(super.getX().getManager()).getView()));
        this.deleteButton.setOnAction(event -> this.resetQuickTransactions());
        this.setPref();
    }

    private void setPref() {
        this.newQuickTransactionButton.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        this.newMovememntButton.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        this.accountsScroll.prefHeightProperty().bind(this.mainPane.prefHeightProperty());
        this.quickTransactionsScroll.setPrefHeight(Screen.getPrimary().getBounds().getHeight());
        this.quickTransactionsScroll.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        this.transactionsScroll.prefHeightProperty().bind(this.mainPane.prefHeightProperty());
        this.quickTransactionsScroll.setFitToWidth(true);
        this.transactionsScroll.setFitToWidth(true);
        this.accountsScroll.setFitToWidth(true);
    }

    @Override
    public final void populateAccounts() {
        this.amountLabel.setText(this.format(super.getX().getTotalAmount()));
        var viewFactory = new ViewFactoryImpl();
        final List<GenericTileView<Account>> fastAccountTiles = new ArrayList<>();
        super.getX().getSortedAccounts().forEach(a -> fastAccountTiles.add(
                new GenericTileViewImpl<>(a, a.getColor(), a.getName(), "",
                        super.getX().getAmount(a))));
        var vbox = viewFactory.createVerticalAutoResizingWithNodes(fastAccountTiles.stream()
                .map(View::getRoot).collect(Collectors.toList()));
        this.accountsScroll.setContent(vbox.getRoot());
    }

    @Override
    public final void populateRecentTransactions() {
        var viewFactory = new ViewFactoryImpl();
        final List<GenericTileView<Transaction>> fastTransactionTiles = new ArrayList<>();
        super.getX().getSortedTodayTransactions().forEach(t -> fastTransactionTiles.add(
                new GenericTileViewImpl<>(t, t.getCategory().getColor(), t.getDescription(),
                        t.getCategory().getName(), (double) t.getAmount() / 100)));
        var vbox = viewFactory.createVerticalAutoResizingWithNodes(fastTransactionTiles.stream()
                .map(View::getRoot).collect(Collectors.toList()));
        this.transactionsScroll.setContent(vbox.getRoot());
    }

    @Override
    public final void populateQuickTransactions() {
        var pane = ViewFactory.verticalWithPadding(RATIO, RATIO, RATIO);
        final List<FinanceMenuButton<FinanceHomePageController>> financeHotKeyButtons = new ArrayList<>();
        super.getX().getSortedQuickTransactions().forEach(qt -> financeHotKeyButtons.add(
                new FinanceMenuButtonImpl<>(qt.getDescription(), c -> c.doQuickTransaction(qt))));
        financeHotKeyButtons.forEach(b -> pane.getChildren().add(b.getButton()));
        financeHotKeyButtons.forEach(b -> b.getButton().setPrefWidth(Screen.getPrimary().getBounds().getWidth()));
        financeHotKeyButtons.forEach(b -> b.getButton().setOnAction(event -> b.getAction(super.getX())));
        this.quickTransactionsScroll.setContent(pane);
    }

    private void show(final View view) {
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) view.getRoot()));
        stage.show();
    }

    private void resetQuickTransactions() {
        var result = super.confirm("Sicuro di voler eliminare tutte le transazioni rapide?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            super.getX().resetQuickTransactions();
        }
    }
}
