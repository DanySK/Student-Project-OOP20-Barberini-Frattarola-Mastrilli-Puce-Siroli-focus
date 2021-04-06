package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.SubscriptionsController;
import oop.focus.finance.model.Transaction;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SubscriptionsViewImpl implements Initializable, SubscriptionsView {

    @FXML
    private ScrollPane subcriptionsScroll;
    @FXML
    private Label monthlyTransactionLabel, annualTransactionLabel;

    private final SubscriptionsController controller;
    private Parent root;

    public SubscriptionsViewImpl(final SubscriptionsController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.SUBS.getPath()));
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
        this.annualTransactionLabel.setText("Spesa annuale -> E " + this.controller.getYearlyExpense());
        this.monthlyTransactionLabel.setText("Spesa mensile -> E " + this.controller.getMonthlyExpense());
    }

    @Override
    public final void showSubscriptions(final List<Transaction> subscriptions) {
        final List<SubscriptionView> subscriptionsTiles = new ArrayList<>();
        subscriptions.forEach(t -> subscriptionsTiles.add(new SubscriptionViewImpl(t)));
        final VBox box = new VBox();
        subscriptionsTiles.forEach(t -> box.getChildren().add(t.getRoot()));
        subscriptionsTiles.forEach(t -> t.getRoot()
                .addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.controller.stopSubscription(t.getSubscription())));
        this.subcriptionsScroll.setContent(box);
    }
}
