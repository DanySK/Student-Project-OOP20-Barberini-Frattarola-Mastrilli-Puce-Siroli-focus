package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oop.focus.finance.controller.FXMLPaths;

import oop.focus.finance.controller.TransactionsController;

public class NewAccountViewImpl extends GenericWindow<TransactionsController> {

    @FXML
    private Label titleLabel, nameLabel, amountLabel, colorLabel;
    @FXML
    private TextField nameTextfield, amountTextfield;
    @FXML
    private ChoiceBox<String> colorChoiche;
    @FXML
    private Button cancelButton, saveButton;

    public NewAccountViewImpl(final TransactionsController transactionsController) {
        super(transactionsController, FXMLPaths.NEWACCOUNT);
    }

    @Override
    public final void populate() {
        this.titleLabel.setText("Nuovo conto");
        this.nameLabel.setText("Nome:");
        this.amountLabel.setText("Importo:                E");
        this.colorLabel.setText("Colore:");
        this.cancelButton.setText("Cancella");
        this.saveButton.setText("Salva");
        this.colorChoiche.setItems(super.getX().getColors());
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
    }

    @Override
    public final void save() {
        if (this.nameTextfield.getText().isEmpty() || isNotNumeric(this.amountTextfield.getText())
                || this.colorChoiche.getValue().isEmpty()) {
            super.allert("I campi non sono stati compilati correttamente.");
        } else {
            super.getX().newAccount(this.nameTextfield.getText(), this.colorChoiche.getValue(),
                        Double.parseDouble(this.amountTextfield.getText()));
            this.close();
        }
    }
}
