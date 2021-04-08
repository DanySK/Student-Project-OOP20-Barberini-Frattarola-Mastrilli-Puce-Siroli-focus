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
import org.joda.time.LocalDateTime;

import java.time.LocalDate;

public class NewTransactionViewImpl extends GenericWindow<FinanceHomePageController> {

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

    public NewTransactionViewImpl(final FinanceHomePageController financeHomePageController, final FXMLPaths path) {
        super(financeHomePageController, path);
    }

    @Override
    protected final void populate() {
        this.titleLabel.setText("Nuova transazione");
        this.descriptionLabel.setText("Descrizione:");
        this.amountLabel.setText("Importo:                E");
        this.categoryLabel.setText("Categoria:");
        this.repetitionLabel.setText("Ripetizione:");
        this.dateLabel.setText("Data:");
        this.accountLabel.setText("Conto:");
        this.cancelButton.setText("Cancella");
        this.saveButton.setText("Salva");
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
                || (this.dataPicker.getValue() != null && this.dataPicker.getValue().isAfter(LocalDate.now()))) {
            super.allert();
        } else {
            super.getX().newTransaction(this.descriptionTextField.getText(),
                    Double.parseDouble(this.amountTextField.getText()), this.categoryChoice.getValue(),
                    this.accountChoice.getValue(), this.dataPicker.getValue() == null ? LocalDateTime.now()
                            : new LocalDateTime(this.dataPicker.getValue().getYear(), this.dataPicker.getValue().getMonthValue(),
                            this.dataPicker.getValue().getDayOfMonth(), 0, 0, 0),
                    this.repetitionChioce.getValue());
            this.close();
        }
    }
}
