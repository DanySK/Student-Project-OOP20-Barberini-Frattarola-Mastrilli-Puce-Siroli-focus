package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.SubscriptionsController;
import oop.focus.finance.model.Transaction;

public class SubscriptionDetailsWindowImpl extends GenericDetailsWindow<SubscriptionsController> {

    @FXML
    private Label titleLabel, dateLabel;
    @FXML
    private Button deleteButton;

    public SubscriptionDetailsWindowImpl(final SubscriptionsController controller, final Transaction subscription) {
        super(controller, subscription, FXMLPaths.TRANSACTIONDETAILS);
    }

    @Override
    public final void edits() {
        this.titleLabel.setText("DETTAGLI ABBONAMENTO");
        this.dateLabel.setText("Data e ora ultimo rinnovo:");
        this.deleteButton.setText("Ferma rinnovo");
    }

    @Override
    public final void save() {
        super.getController().stopSubscription(super.getX());
        this.close();
    }
}
