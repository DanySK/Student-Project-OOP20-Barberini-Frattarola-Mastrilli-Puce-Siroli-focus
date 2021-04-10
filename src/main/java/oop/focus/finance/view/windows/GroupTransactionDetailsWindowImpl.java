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
        this.descriptionLabel.setText("Fatta da:");
        this.categoryLabel.setText("Per:");
        this.dateLabel.setText("Data:");
        this.accountLabel.setText("Descrizione");
        this.amountLabel.setText("Importo:");
        this.subscriptionLabel.setText("Importo a testa:");
    }

    @Override
    public final void populateDynamicLabels() {
        this.dataAccountLabel.setText(super.getX().getDescription());
        this.dataDescriptionLabel.setText(super.getX().getMadeBy().getName());
        this.dataCategoryLabel.setText(super.getX().getForList().toString());
        this.dataDateLabel.setText(super.getX().getDate().toString());
        DecimalFormat df = new DecimalFormat("#.00");
        this.dataAmountLabel.setText("E " + df.format((double) super.getX().getAmount() / 100));
        this.dataSubscriptionLabel.setText("E " + df.format((double) super.getX().getAmount() / (super.getX().getForList().size() * 100)));
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
