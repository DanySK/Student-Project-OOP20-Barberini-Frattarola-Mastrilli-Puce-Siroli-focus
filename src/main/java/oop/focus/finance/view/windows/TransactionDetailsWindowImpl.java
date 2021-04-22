package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import oop.focus.common.Repetition;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.model.Transaction;
import oop.focus.finance.view.StaticAllerts;
import oop.focus.finance.view.StaticFormats;

import java.util.Optional;

/**
 * Class that implements the detail view of a transaction.
 */
public class TransactionDetailsWindowImpl extends GenericDetailsWindow {

    @FXML
    private Label dataDescriptionLabel, dataCategoryLabel, dataDateLabel,
            dataAccountLabel, dataAmountLabel, dataSubscriptionLabel;

    private final TransactionsController controller;
    private final Transaction transactions;

    public TransactionDetailsWindowImpl(final TransactionsController controller, final Transaction transaction) {
        this.controller = controller;
        this.transactions = transaction;
        this.loadFXML(FXMLPaths.TRANSACTIONDETAILS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populateDynamicLabels() {
        this.dataDescriptionLabel.setText(this.transactions.getDescription());
        this.dataCategoryLabel.setText(this.transactions.getCategory().getName());
        this.dataDateLabel.setText(StaticFormats.formatDate(this.transactions.getDate()));
        this.dataAccountLabel.setText(this.transactions.getAccount().getName());
        this.dataAmountLabel.setText(StaticFormats.formatAmount((double) this.transactions.getAmount() / 100));
        this.dataSubscriptionLabel.setText(this.transactions.getRepetition().equals(Repetition.ONCE) ? "No"
                : this.transactions.getRepetition().getName());
    }

    /**
     * {@inheritDoc}
     * If the user confirms this, the transaction is deleted.
     */
    @Override
    public final void save() {
        final Optional<ButtonType> result = StaticAllerts.confirm("Sicuro di voler elminare la transazione?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            this.controller.deleteTransaction(this.transactions);
        }
        this.close();
    }
}
