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

public class NewTransactionViewImpl extends GenericWindow<FinanceHomePageController> {

    private static final int MAX_HOURS = 23;
    private static final int MAX_MINUTES = 59;

    @FXML
    private Label titleLabel;
    @FXML
    private TextField descriptionTextField, amountTextField, hoursTextField, minutesTextField;
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

    public NewTransactionViewImpl(final FinanceHomePageController financeHomePageController) {
        super(financeHomePageController, FXMLPaths.NEWMOVEMENT);
    }

    @Override
    public final void populate() {
        this.titleLabel.setText("NUOVA TRANSAZIONE");
        this.categoryChoice.setItems(super.getX().getCategories());
        this.accountChoice.setItems(super.getX().getAccounts());
        this.repetitionChioce.setItems(super.getX().getRepetitions());
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
    }

    @Override
    public final void save() {
        if (this.descriptionTextField.getText().isEmpty() || isNotNumeric(this.amountTextField.getText())
                || this.categoryChoice.getValue() == null || this.accountChoice.getValue() == null
                || this.repetitionChioce.getValue() == null
                || (!this.hoursTextField.getText().isEmpty() && (isNotNumeric(this.hoursTextField.getText())
                    || Integer.parseInt(this.hoursTextField.getText()) < 0
                    || Integer.parseInt(this.hoursTextField.getText()) > MAX_HOURS))
                || (!this.minutesTextField.getText().isEmpty() && (isNotNumeric(this.minutesTextField.getText())
                    || (Integer.parseInt(this.hoursTextField.getText()) < 0
                    || Integer.parseInt(this.hoursTextField.getText()) > MAX_MINUTES)))) {
            super.allert("I campi non sono stati compilati correttamente.");
        } else {
            try {
                super.getX().newTransaction(this.descriptionTextField.getText(), this.amountTextField.getText(),
                        this.categoryChoice.getValue(), this.accountChoice.getValue(), this.dataPicker.getValue(),
                        this.hoursTextField.getText(), this.minutesTextField.getText(), this.repetitionChioce.getValue());
            } catch (Exception e) {
                super.allert("Non posso eseguire una transazione in una data futura.");
            }
            this.close();
        }
    }
}
