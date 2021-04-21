package oop.focus.finance.view.bases;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class that implements the display of the finance part of the home page.
 * Once the class is created, the home page is populated with all accounts,
 * with quick transactions and with the current day's transactions.
 */
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
    private Button newMovementButton, newQuickTransactionButton, deleteButton;

    public FinanceHomePageViewImpl(final FinanceHomePageController controller) {
        super(controller, FXMLPaths.HOMEPAGE);
    }

    @Override
    public final void populate() {
        this.populateAccounts();
        this.populateRecentTransactions();
        this.populateQuickTransactions();
        this.newMovementButton.setOnAction(event ->
                this.show(new NewTransactionControllerImpl(super.getX().getManager()).getView()));
        this.newQuickTransactionButton.setOnAction(event ->
                this.show(new NewQuickTransactionControllerImpl(super.getX().getManager()).getView()));
        this.deleteButton.setOnAction(event -> this.resetQuickTransactions());
        this.setPref();
    }

    /**
     * Method that takes care of the resize of the view.
     */
    private void setPref() {
        final Rectangle2D screen = Screen.getPrimary().getBounds();
        this.newQuickTransactionButton.setPrefWidth(screen.getWidth());
        this.newMovementButton.setPrefWidth(screen.getWidth());
        this.quickTransactionsScroll.setPrefHeight(screen.getHeight());
        this.quickTransactionsScroll.setPrefWidth(screen.getWidth());
        this.accountsScroll.prefHeightProperty().bind(this.mainPane.prefHeightProperty());
        this.transactionsScroll.prefHeightProperty().bind(this.mainPane.prefHeightProperty());
        this.quickTransactionsScroll.setFitToWidth(true);
        this.transactionsScroll.setFitToWidth(true);
        this.accountsScroll.setFitToWidth(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populateAccounts() {
        this.amountLabel.setText(this.format(super.getX().getTotalAmount()));
        this.amountLabel.setTextFill(Color.valueOf(super.getX().getTotalAmount() > 0 ? "008f39" : "cc0605"));
        final ViewFactory viewFactory = new ViewFactoryImpl();
        final List<GenericTileView<Account>> fastAccountTiles = new ArrayList<>();
        super.getX().getSortedAccounts().forEach(a -> fastAccountTiles.add(
                new GenericTileViewImpl<>(a, a.getColor(), a.getName(), "", super.getX().getAmount(a))));
        final View vbox = viewFactory.createVerticalAutoResizingWithNodes(fastAccountTiles.stream()
                .map(View::getRoot).collect(Collectors.toList()));
        this.accountsScroll.setContent(vbox.getRoot());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populateRecentTransactions() {
        final ViewFactory viewFactory = new ViewFactoryImpl();
        final List<GenericTileView<Transaction>> fastTransactionTiles = new ArrayList<>();
        super.getX().getSortedTodayTransactions().forEach(t -> fastTransactionTiles.add(
                new GenericTileViewImpl<>(t, t.getCategory().getColor(), t.getDescription(),
                        t.getCategory().getName(), (double) t.getAmount() / 100)));
        final View vbox = viewFactory.createVerticalAutoResizingWithNodes(fastTransactionTiles.stream()
                .map(View::getRoot).collect(Collectors.toList()));
        this.transactionsScroll.setContent(vbox.getRoot());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populateQuickTransactions() {
        final Pane pane = ViewFactory.verticalWithPadding(RATIO, RATIO, RATIO);
        final List<FinanceMenuButton<FinanceHomePageController>> financeHotKeyButtons = new ArrayList<>();
        super.getX().getSortedQuickTransactions().forEach(qt -> financeHotKeyButtons.add(
                new FinanceMenuButtonImpl<>(qt.getDescription(), c -> c.doQuickTransaction(qt))));
        financeHotKeyButtons.forEach(b -> {
            pane.getChildren().add(b.getButton());
            b.getButton().setPrefWidth(Screen.getPrimary().getBounds().getWidth());
            b.getButton().setOnAction(event -> b.action(super.getX()));
        });
        this.quickTransactionsScroll.setContent(pane);
    }

    /**
     * Method that displays a view on the screen.
     *
     * @param view to be shown
     */
    private void show(final View view) {
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) view.getRoot()));
        stage.show();
    }

    /**
     * Method that after confirmation deletes all quick transactions.
     */
    private void resetQuickTransactions() {
        final Optional<ButtonType> result = super.confirm("Sicuro di voler eliminare tutte le transazioni rapide?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            super.getX().resetQuickTransactions();
        }
    }
}
