package oop.focus.finance.view.bases;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.SubscriptionsController;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.StaticFormats;
import oop.focus.finance.view.tiles.GenericTileView;
import oop.focus.finance.view.tiles.FinanceTileViewImplImpl;
import oop.focus.finance.view.windows.SubscriptionDetailsWindowImpl;
import oop.focus.statistics.view.ViewFactory;
import oop.focus.statistics.view.ViewFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that implements the view of subscriptions.
 */
public class SubscriptionsViewImplImpl extends FinanceViewImpl implements SubscriptionsView {

    @FXML
    private BorderPane mainPane;
    @FXML
    private ScrollPane subscriptionsScroll;
    @FXML
    private Label monthlyLabel, annualLabel, monthlyTransactionLabel, annualTransactionLabel;

    private final SubscriptionsController controller;

    public SubscriptionsViewImplImpl(final SubscriptionsController controller) {
        this.controller = controller;
        this.loadFXML(FXMLPaths.SUBS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populate() {
        this.annualTransactionLabel.setText(StaticFormats.formatAmount(this.controller.getYearlyExpense()));
        this.monthlyTransactionLabel.setText(StaticFormats.formatAmount(this.controller.getMonthlyExpense()));
        this.setPref();
    }

    /**
     * Method that takes care of the resize of the view.
     */
    private void setPref() {
        this.monthlyLabel.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.annualLabel.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.monthlyTransactionLabel.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.annualTransactionLabel.prefWidthProperty().bind(this.mainPane.prefWidthProperty());
        this.subscriptionsScroll.setFitToWidth(true);
    }

    /**
     * {@inheritDoc}
     */
    public final void showSubscriptions(final List<Transaction> subscriptions) {
        final ViewFactory viewFactory = new ViewFactoryImpl();
        final List<GenericTileView<Transaction>> subscriptionsTiles = new ArrayList<>();
        subscriptions.forEach(t -> subscriptionsTiles.add(
                new FinanceTileViewImplImpl<>(t, t.getCategory().getColor(), t.getDescription(),
                        t.getRepetition().getName(), this.controller.getTransactionAmount(t))));
        final View vbox = viewFactory.createVerticalAutoResizingWithNodes(subscriptionsTiles.stream()
                .map(View::getRoot).collect(Collectors.toList()));
        subscriptionsTiles.forEach(t -> t.getRoot()
                .addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.showDetails(t.getElement())));
        this.subscriptionsScroll.setContent(vbox.getRoot());
    }

    /**
     * Method that shows the details of a transaction.
     */
    private void showDetails(final Transaction subscription) {
        final View details = new SubscriptionDetailsWindowImpl(this.controller, subscription);
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) details.getRoot()));
        stage.show();
    }
}
