package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import oop.focus.common.Repetition;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.model.Transaction;

import java.text.DecimalFormat;

public abstract class GenericDetailsWindow<X> extends GenericWindow<Transaction> implements FinanceDetailsWindow<X> {

    @FXML
    private Label titleLabel, descriptionLabel, categoryLabel, dateLabel, accountLabel, amountLabel, subscriptionLabel,
            dataDescriptionLabel, dataCategoryLabel, dataDateLabel, dataAccountLabel, dataAmountLabel, dataSubscriptionLabel;
    @FXML
    private Button closeButton, deleteButton;

    private final X controller;

    public GenericDetailsWindow(final X controller, final Transaction transaction, final FXMLPaths path) {
        super(transaction, path);
        this.controller = controller;
    }

    @Override
    protected final void populate() {
        this.populateStaticLabels();
        this.populateDynamicLabels();
        this.populateButtons();
        this.edits();
    }

    private void populateButtons() {
        this.deleteButton.setText("Elimina");
        this.closeButton.setText("Chiudi");
        this.deleteButton.setOnAction(event -> this.save());
        this.closeButton.setOnAction(event -> this.close());
    }

    private void populateDynamicLabels() {
        this.dataDescriptionLabel.setText(super.getX().getDescription());
        this.dataCategoryLabel.setText(super.getX().getCategory().getName());
        this.dataDateLabel.setText(super.getX().getDateToString());
        this.dataAccountLabel.setText(super.getX().getAccount().toString());
        DecimalFormat df = new DecimalFormat("#.00");
        this.dataAmountLabel.setText("E " + df.format((double) super.getX().getAmount() / 100));
        this.dataSubscriptionLabel.setText(super.getX().getRepetition().equals(Repetition.ONCE) ? "No"
                : super.getX().getRepetition().getName());
    }

    private void populateStaticLabels() {
        this.titleLabel.setText("DETTAGLI TRANSAZIONE");
        this.descriptionLabel.setText("Descrizione:");
        this.categoryLabel.setText("Categoria");
        this.dateLabel.setText("Data e ora:");
        this.accountLabel.setText("Conto");
        this.amountLabel.setText("Importo:");
        this.subscriptionLabel.setText("Abbonamento:");
    }

    @Override
    public final X getController() {
        return this.controller;
    }
}
