package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.SubscriptionsController;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.StaticAllerts;
import oop.focus.finance.view.StaticFormats;

import java.util.Optional;

/**
 * Class that implements the detail view of a subscription.
 */
public class SubscriptionDetailsWindowImpl extends GenericDetailsWindow {

    @FXML
    private Label titleLabel, dateLabel, dataDescriptionLabel, dataCategoryLabel, dataDateLabel, dataAccountLabel,
            dataAmountLabel, dataSubscriptionLabel;
    @FXML
    private Button deleteButton, closeButton;

    private final SubscriptionsController controller;
    private final Transaction subscription;

    public SubscriptionDetailsWindowImpl(final SubscriptionsController controller, final Transaction subscription) {
        this.controller = controller;
        this.subscription = subscription;
        this.loadFXML(FXMLPaths.TRANSACTIONDETAILS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populateStaticLabels() {
        this.titleLabel.setText("DETTAGLI ABBONAMENTO");
        this.dateLabel.setText("Data e ora ultimo rinnovo:");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populateDynamicLabels() {
        this.dataDescriptionLabel.setText(this.subscription.getDescription());
        this.dataCategoryLabel.setText(this.subscription.getCategory().getName());
        this.dataDateLabel.setText(StaticFormats.formatDate(this.subscription.getDate()));
        this.dataAccountLabel.setText(this.subscription.getAccount().getName());
        this.dataAmountLabel.setText(StaticFormats.formatAmount((double) this.subscription.getAmount() / 100));
        this.dataSubscriptionLabel.setText(this.subscription.getRepetition().getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populateButtons() {
        this.deleteButton.setText("Stop");
        this.closeButton.setText("Chiudi");
        this.deleteButton.setOnAction(event -> this.save());
        this.closeButton.setOnAction(event -> this.close());
    }

    /**
     * {@inheritDoc}
     * If the user confirms this, the subscription is stopped.
     */
    @Override
    public final void save() {
        final Optional<ButtonType> result = StaticAllerts.confirm("Sicuro di non voler piu' rinnovare l'abbonamento?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            this.controller.stopSubscription(this.subscription);
        }
        this.close();
    }
}
