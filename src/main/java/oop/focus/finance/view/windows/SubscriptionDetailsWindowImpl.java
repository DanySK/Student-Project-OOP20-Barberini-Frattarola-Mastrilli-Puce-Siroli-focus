package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.SubscriptionsController;
import oop.focus.finance.model.Transaction;

import java.util.Optional;

/**
 * Class that implements the detail view of a subscription.
 */
public class SubscriptionDetailsWindowImpl extends GenericDetailsWindow<SubscriptionsController, Transaction> {

    @FXML
    private Label titleLabel, dateLabel, dataDescriptionLabel, dataCategoryLabel, dataDateLabel, dataAccountLabel,
            dataAmountLabel, dataSubscriptionLabel;
    @FXML
    private Button deleteButton, closeButton;

    public SubscriptionDetailsWindowImpl(final SubscriptionsController controller, final Transaction subscription) {
        super(controller, subscription, FXMLPaths.TRANSACTIONDETAILS);
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
        this.dataDescriptionLabel.setText(super.getX().getDescription());
        this.dataCategoryLabel.setText(super.getX().getCategory().getName());
        this.dataDateLabel.setText(super.getX().getDateToString());
        this.dataAccountLabel.setText(super.getX().getAccount().getName());
        this.dataAmountLabel.setText(this.format((double) super.getX().getAmount() / 100));
        this.dataSubscriptionLabel.setText(super.getX().getRepetition().getName());
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
        final Optional<ButtonType> result = super.confirm("Sicuro di non voler piu' rinnovare l'abbonamento?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            super.getController().stopSubscription(super.getX());
        }
        this.close();
    }
}
