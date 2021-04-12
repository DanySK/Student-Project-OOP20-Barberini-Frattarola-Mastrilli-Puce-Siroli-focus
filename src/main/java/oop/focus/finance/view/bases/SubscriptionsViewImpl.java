package oop.focus.finance.view.bases;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.common.View;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.SubscriptionDetailsControllerImpl;
import oop.focus.finance.controller.SubscriptionsController;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.tiles.GenericTileView;
import oop.focus.finance.view.tiles.GenericTileViewImpl;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionsViewImpl extends GenericView<SubscriptionsController> implements SubscriptionsView {

    @FXML
    private VBox subcriptionsVBox;
    @FXML
    private Label monthlyTransactionLabel, annualTransactionLabel;

    public SubscriptionsViewImpl(final SubscriptionsController controller) {
        super(controller, FXMLPaths.SUBS);
    }

    @Override
    public final void populate() {
        this.annualTransactionLabel.setText(this.format(super.getX().getYearlyExpense()));
        this.monthlyTransactionLabel.setText(this.format(super.getX().getMonthlyExpense()));
    }

    @Override
    public final void showSubscriptions(final List<Transaction> subscriptions) {
        this.subcriptionsVBox.getChildren().clear();
        final List<GenericTileView<Transaction>> subscriptionsTiles = new ArrayList<>();
        subscriptions.forEach(t -> subscriptionsTiles.add(
                new GenericTileViewImpl<>(t, t.getDescription() + "      " + t.getRepetition().getName(),
                this.format(super.getX().getTransactionAmount(t)))));
        subscriptionsTiles.forEach(t -> this.subcriptionsVBox.getChildren().add(t.getRoot()));
        subscriptionsTiles.forEach(t -> t.getRoot()
                .addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.showDetails(t.getElement())));
    }

    private void showDetails(final Transaction subscription) {
        final View details = new SubscriptionDetailsControllerImpl(super.getX().getManager(), subscription).getView();
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) details.getRoot()));
        stage.show();
    }
}
