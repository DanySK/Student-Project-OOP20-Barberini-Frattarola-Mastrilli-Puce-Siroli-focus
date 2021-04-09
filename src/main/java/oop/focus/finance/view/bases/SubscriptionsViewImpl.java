package oop.focus.finance.view.bases;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.SubscriptionsController;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.tiles.GenericTileView;
import oop.focus.finance.view.tiles.GenericTileViewImpl;
import oop.focus.finance.view.windows.SubscriptionDetailsWindowImpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionsViewImpl extends GenericView<SubscriptionsController> implements SubscriptionsView {

    @FXML
    private ScrollPane subcriptionsScroll;
    @FXML
    private Label monthlyTransactionLabel, annualTransactionLabel;

    public SubscriptionsViewImpl(final SubscriptionsController controller) {
        super(controller, FXMLPaths.SUBS);
    }

    @Override
    public final void populate() {
        this.annualTransactionLabel.setText("Spesa annuale -> E " + super.getX().getYearlyExpense());
        this.monthlyTransactionLabel.setText("Spesa mensile -> E " + super.getX().getMonthlyExpense());
    }

    @Override
    public final void showSubscriptions(final List<Transaction> subscriptions) {
        final List<GenericTileView<Transaction>> subscriptionsTiles = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#0.00");
        subscriptions.forEach(t -> subscriptionsTiles.add(new GenericTileViewImpl<>(t, t.getDescription(),
                "E " + df.format((double) t.getAmount() / 100))));
        final VBox box = new VBox();
        subscriptionsTiles.forEach(t -> box.getChildren().add(t.getRoot()));
        subscriptionsTiles.forEach(t -> t.getRoot()
                .addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.showDetails(t.getElement())));
        this.subcriptionsScroll.setContent(box);
    }

    private void showDetails(final Transaction subscription) {
        final View details = new SubscriptionDetailsWindowImpl(super.getX(), subscription);
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) details.getRoot()));
        stage.show();
    }
}
