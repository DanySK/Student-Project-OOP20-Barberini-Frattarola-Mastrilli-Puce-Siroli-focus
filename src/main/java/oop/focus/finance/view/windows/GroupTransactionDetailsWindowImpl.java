package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import oop.focus.finance.controller.DetailsController;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.homepage.model.Person;

import java.util.stream.Collectors;

public class GroupTransactionDetailsWindowImpl extends GenericWindow<DetailsController<GroupTransaction>> {

    @FXML
    private Label titleLabel, categoryLabel, accountLabel, subscriptionLabel, dataDescriptionLabel, dataCategoryLabel,
            dataDateLabel, dataAccountLabel, dataAmountLabel, dataSubscriptionLabel, secondEuroLabel;
    @FXML
    private Button closeButton, deleteButton;

    public GroupTransactionDetailsWindowImpl(final DetailsController<GroupTransaction> controller) {
        super(controller, FXMLPaths.TRANSACTIONDETAILS);
    }

    @Override
    public final void populate() {
        this.populateStaticLabels();
        this.populateDynamicLabels();
        this.populateButtons();
    }

    private void populateStaticLabels() {
        this.titleLabel.setText("DETTAGLI TRANSAZIONE DI GRUPPO");
        this.categoryLabel.setText("Fatta da:");
        this.accountLabel.setText("Per:");
        this.subscriptionLabel.setText("Importo a testa:");
        this.secondEuroLabel.setVisible(true);
    }

    private void populateDynamicLabels() {
        this.dataDescriptionLabel.setText(super.getX().getElement().getDescription());
        this.dataCategoryLabel.setText(super.getX().getElement().getMadeBy().getName());
        this.dataDateLabel.setText(super.getX().getElement().getDateToString());
        this.dataAccountLabel.setText(this.getFormattedForList());
        this.dataAmountLabel.setText(this.format((double) super.getX().getElement().getAmount() / 100));
        this.dataSubscriptionLabel.setText(this.format(this.getAmountPerPerson()));
    }

    private void populateButtons() {
        this.deleteButton.setText("Elimina");
        this.closeButton.setText("Chiudi");
        this.deleteButton.setOnAction(event -> this.save());
        this.closeButton.setOnAction(event -> this.close());
    }

    @Override
    public final void save() {
        var result = super.confirm("Sicuro di voler elminare questa transazione di gruppo?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            super.getX().deleteElement(super.getX().getElement());
        }
        this.close();
    }

    private double getAmountPerPerson() {
        return (double) super.getX().getElement().getAmount() / (super.getX().getElement().getForList().size() * 100);
    }

    private String getFormattedForList() {
        return super.getX().getElement().getForList().stream().map(Person::getName).collect(Collectors.joining(", "));
    }
}
