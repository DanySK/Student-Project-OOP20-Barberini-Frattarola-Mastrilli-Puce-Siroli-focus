package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.GroupController;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.view.StaticAllerts;
import oop.focus.finance.view.StaticFormats;

import java.util.Optional;

/**
 * Class that implements the detail view of a group transaction.
 */
public class GroupTransactionDetailsWindowImpl extends GenericDetailsWindow {

    @FXML
    private Label titleLabel, categoryLabel, accountLabel, subscriptionLabel, dataDescriptionLabel, dataCategoryLabel,
            dataDateLabel, dataAccountLabel, dataAmountLabel, dataSubscriptionLabel, secondEuroLabel;
    @FXML
    private Button deleteButton;

    private final GroupController controller;
    private final GroupTransaction transaction;

    public GroupTransactionDetailsWindowImpl(final GroupController controller, final GroupTransaction transaction) {
        this.controller = controller;
        this.transaction = transaction;
        this.loadFXML(FXMLPaths.TRANSACTIONDETAILS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populateStaticLabels() {
        this.titleLabel.setText("DETTAGLI TRANSAZIONE DI GRUPPO");
        this.categoryLabel.setText("Fatta da:");
        this.accountLabel.setText("Per:");
        this.subscriptionLabel.setText("Importo a testa:");
        this.secondEuroLabel.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populateDynamicLabels() {
        this.dataDescriptionLabel.setText(this.transaction.getDescription());
        this.dataCategoryLabel.setText(this.transaction.getMadeBy().getName());
        this.dataDateLabel.setText(StaticFormats.formatDate(this.transaction.getDate()));
        this.dataAccountLabel.setText(StaticFormats.formatPersonList(this.transaction.getForList()));
        this.dataAmountLabel.setText(StaticFormats.formatAmount((double) this.transaction.getAmount() / 100));
        this.dataSubscriptionLabel.setText(StaticFormats.formatAmount(this.getAmountPerPerson()));
    }

    /**
     * {@inheritDoc}
     * If the user confirms this, the group transaction is deleted.
     */
    @Override
    public final void save() {
        final Optional<ButtonType> result = StaticAllerts.confirm("Sicuro di voler elminare questa transazione di gruppo?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            this.controller.deleteTransaction(this.transaction);
        }
        this.close();
    }

    /**
     * @return the expense per person of a group transaction
     */
    private double getAmountPerPerson() {
        return (double) this.transaction.getAmount() / (this.transaction.getForList().size() * 100);
    }
}
