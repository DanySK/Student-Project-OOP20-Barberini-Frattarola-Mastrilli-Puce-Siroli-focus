package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.GroupController;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.homepage.model.Person;

import java.text.DecimalFormat;
import java.util.stream.Collectors;

public class GroupTransactionDetailsWindowImpl extends GenericDetailsWindow<GroupController, GroupTransaction> {

    @FXML
    private Label titleLabel, categoryLabel, accountLabel, subscriptionLabel, dataDescriptionLabel, dataCategoryLabel,
            dataDateLabel, dataAccountLabel, dataAmountLabel, dataSubscriptionLabel, secondEuroLabel;
    @FXML
    private Button deleteButton;

    public GroupTransactionDetailsWindowImpl(final GroupController controller, final GroupTransaction transaction) {
        super(controller, transaction, FXMLPaths.TRANSACTIONDETAILS);
    }

    @Override
    public final void populateStaticLabels() {
        this.titleLabel.setText("DETTAGLI TRANSAZIONE DI GRUPPO");
        this.categoryLabel.setText("Fatta da:");
        this.accountLabel.setText("Per:");
        this.subscriptionLabel.setText("Importo a testa:");
        this.secondEuroLabel.setVisible(true);
    }

    @Override
    public final void populateDynamicLabels() {
        this.dataDescriptionLabel.setText(super.getX().getDescription());
        this.dataCategoryLabel.setText(super.getX().getMadeBy().getName());
        this.dataDateLabel.setText(super.getX().getDateToString());
        this.dataAccountLabel.setText(super.getX().getForList().stream().map(Person::getName).collect(Collectors.joining(", ")));
        DecimalFormat df = new DecimalFormat("#.00");
        this.dataAmountLabel.setText("" + df.format((double) super.getX().getAmount() / 100));
        this.dataSubscriptionLabel.setText("" + df.format((double) super.getX().getAmount() / (super.getX().getForList().size() * 100)));
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
