package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import oop.focus.common.Repetition;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.model.Transaction;

import java.util.Optional;

/**
 * Class that implements the detail view of a transaction.
 */
public class TransactionDetailsWindowImpl extends GenericDetailsWindow<TransactionsController, Transaction> {

    @FXML
    private Label dataDescriptionLabel, dataCategoryLabel, dataDateLabel,
            dataAccountLabel, dataAmountLabel, dataSubscriptionLabel;

    public TransactionDetailsWindowImpl(final TransactionsController controller, final Transaction transaction) {
        super(controller, transaction, FXMLPaths.TRANSACTIONDETAILS);
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
        this.dataSubscriptionLabel.setText(super.getX().getRepetition().equals(Repetition.ONCE) ? "No"
                : super.getX().getRepetition().getName());
    }

    /**
     * {@inheritDoc}
     * If the user confirms this, the transaction is deleted.
     */
    @Override
    public final void save() {
        final Optional<ButtonType> result = super.confirm("Sicuro di voler elminare la transazione?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            super.getController().deleteTransaction(super.getX());
        }
        this.close();
    }
}
