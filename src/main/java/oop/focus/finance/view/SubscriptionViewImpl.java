package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.SubscriptionsController;
import oop.focus.finance.model.Transaction;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SubscriptionViewImpl implements Initializable, SubscriptionView {

    @FXML
    private Label descriptionLabel;
    @FXML
    private Label amountLabel;

    private final Transaction subscription;
    private Parent root;

    public SubscriptionViewImpl(final SubscriptionsController controller, final Transaction subscription) {
        this.subscription = subscription;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.GENERICTILE.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.descriptionLabel.setText(this.subscription.getDescription());
        this.amountLabel.setText("E " + (double) this.subscription.getAmount() / 100);
    }

    @Override
    public final Transaction getSubscription() {
        return this.subscription;
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }
}
