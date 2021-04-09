package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.GroupController;
import oop.focus.finance.model.GroupTransaction;

import java.text.DecimalFormat;

public class GroupTransactionDetailsWindowImpl extends GenericDetailsWindow<GroupController, GroupTransaction> {

    @FXML
    private Label titleLabel, descriptionLabel, categoryLabel, dateLabel, accountLabel, amountLabel, subscriptionLabel,
            dataDescriptionLabel, dataCategoryLabel, dataDateLabel, dataAccountLabel, dataAmountLabel, dataSubscriptionLabel;
    @FXML
    private Button deleteButton;

    public GroupTransactionDetailsWindowImpl(final GroupController controller, final GroupTransaction transaction) {
        super(controller, transaction, FXMLPaths.TRANSACTIONDETAILS);
    }

    @Override
    public final void populateStaticLabels() {
        this.titleLabel.setText("DETTAGLI TRANSAZIONE DI GRUPPO");
        this.descriptionLabel.setText("Descrizione:");
        this.categoryLabel.setText("Fatta da:");
        this.dateLabel.setText("Per:");
        this.amountLabel.setText("Importo:");
    }

    @Override
    public final void populateDynamicLabels() {
        this.dataDescriptionLabel.setText(super.getX().getDescription());
        this.dataCategoryLabel.setText(super.getX().getMadeBy().getName());
        this.dataDateLabel.setText(super.getX().getForList().toString());
        DecimalFormat df = new DecimalFormat("#.00");
        this.dataAmountLabel.setText("E " + df.format((double) super.getX().getAmount() / 100));
    }

    @Override
    public final void save() {
        var result = super.confirm("Sicuro di voler elminare questa transazione di gruppo?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            super.getController().deleteTransaction(super.getX());
        }
        this.close();
    }
}
