package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oop.focus.common.Repetition;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.FinanceHomePageController;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;

public class NewQuickTransactionViewImpl extends GenericWindow<FinanceHomePageController> {

    @FXML
    private Label titleLabel, descriptionLabel, amountLabel, categoryLabel, repetitionLabel, dateLabel, accountLabel;
    @FXML
    private TextField descriptionTextField, amountTextField;
    @FXML
    private DatePicker dataPicker;
    @FXML
    private ChoiceBox<Category> categoryChoice;
    @FXML
    private ChoiceBox<Account> accountChoice;
    @FXML
    private ChoiceBox<Repetition> repetitionChioce;
    @FXML
    private Button cancelButton, saveButton;

    public NewQuickTransactionViewImpl(final FinanceHomePageController financeHomePageController) {
        super(financeHomePageController, FXMLPaths.NEWMOVEMENT);
    }

    @Override
    public final void populate() {
        this.titleLabel.setText("Nuova transazione rapida");
        this.descriptionLabel.setText("Descrizione:");
        this.amountLabel.setText("Importo:                E");
        this.categoryLabel.setText("Categoria:");
        this.accountLabel.setText("Conto:");
        this.cancelButton.setText("Cancella");
        this.saveButton.setText("Salva");
        this.dataPicker.setVisible(false);
        this.repetitionChioce.setVisible(false);
        this.categoryChoice.setItems(super.getX().getCategories());
        this.accountChoice.setItems(super.getX().getAccounts());
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
    }

    @Override
    public final void save() {
        if (this.descriptionTextField.getText().isEmpty() || isNotNumeric(this.amountTextField.getText())
                || this.categoryChoice.getValue() == null || this.accountChoice.getValue() == null) {
            super.allert("I campi non sono stati compilati correttamente.");
        } else {
            super.getX().newQuickTransaction(this.descriptionTextField.getText(),
                    Double.parseDouble(this.amountTextField.getText()), this.categoryChoice.getValue(),
                    this.accountChoice.getValue());
            this.close();
        }
    }
}
