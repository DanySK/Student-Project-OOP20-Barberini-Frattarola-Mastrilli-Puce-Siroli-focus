package oop.focus.finance.view.bases;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.SubscriptionsController;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.tiles.GenericTileView;
import oop.focus.finance.view.tiles.GenericTileViewImpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionsViewImpl extends GenericView<SubscriptionsController> implements SubscriptionsView {

    @FXML
    private ScrollPane subcriptionsScroll;
    @FXML
    private Label monthlyTransactionLabel, annualTransactionLabel;

    public SubscriptionsViewImpl(final SubscriptionsController controller, final FXMLPaths path) {
        super(controller, path);
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
                .addEventHandler(MouseEvent.MOUSE_CLICKED, event -> super.getX().stopSubscription(t.getElement())));
        this.subcriptionsScroll.setContent(box);
    }
}
