package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import oop.focus.common.Repetition;
import oop.focus.finance.controller.DetailsController;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.model.Transaction;

public class TransactionDetailsWindowImpl extends GenericWindow<DetailsController<Transaction>> {

    @FXML
    private Label dataDescriptionLabel, dataCategoryLabel, dataDateLabel,
            dataAccountLabel, dataAmountLabel, dataSubscriptionLabel;
    @FXML
    private Button deleteButton, closeButton;

    public TransactionDetailsWindowImpl(final DetailsController<Transaction> controller) {
        super(controller, FXMLPaths.TRANSACTIONDETAILS);
    }

    @Override
    public final void populate() {
        this.populateDynamicLabels();
        this.populateButtons();
    }

    private void populateDynamicLabels() {
        this.dataDescriptionLabel.setText(super.getX().getElement().getDescription());
        this.dataCategoryLabel.setText(super.getX().getElement().getCategory().getName());
        this.dataDateLabel.setText(super.getX().getElement().getDateToString());
        this.dataAccountLabel.setText(super.getX().getElement().getAccount().toString());
        this.dataAmountLabel.setText(this.format((double) super.getX().getElement().getAmount() / 100));
        this.dataSubscriptionLabel.setText(super.getX().getElement().getRepetition().equals(Repetition.ONCE) ? "No"
                : super.getX().getElement().getRepetition().getName());
    }

    private void populateButtons() {
        this.deleteButton.setText("Elimina");
        this.closeButton.setText("Chiudi");
        this.deleteButton.setOnAction(event -> this.save());
        this.closeButton.setOnAction(event -> this.close());
    }

    @Override
    public final void save() {
        final var result = super.confirm("Sicuro di voler elminare la transazione?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            super.getX().deleteElement(super.getX().getElement());
        }
        this.close();
    }
}
