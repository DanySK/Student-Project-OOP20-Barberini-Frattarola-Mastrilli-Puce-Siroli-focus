package oop.focus.finance.view.windows;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oop.focus.common.Repetition;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.NewCategoryController;
import oop.focus.finance.controller.NewCategoryControllerImpl;
import oop.focus.finance.controller.NewTransactionController;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;
import oop.focus.finance.view.StaticAllerts;
import oop.focus.finance.view.StaticFormats;
import org.joda.time.LocalDateTime;

import java.time.LocalDate;

/**
 * Class that implements the view of creating a new transaction.
 */
public class NewTransactionViewImpl extends GenericWindow {

    @FXML
    private Label titleLabel;
    @FXML
    private TextField descriptionTextField, amountTextField, hoursTextField, minutesTextField;
    @FXML
    private DatePicker dataPicker;
    @FXML
    private ComboBox<Category> categoryChoice;
    @FXML
    private ComboBox<Account> accountChoice;
    @FXML
    private ComboBox<Repetition> repetitionChioce;
    @FXML
    private ChoiceBox<String> typeChoice;
    @FXML
    private Button cancelButton, saveButton, newCategoryButton;

    private final NewTransactionController controller;

    public NewTransactionViewImpl(final NewTransactionController controller) {
        this.controller = controller;
        this.loadFXML(FXMLPaths.NEWMOVEMENT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populate() {
        this.titleLabel.setText("NUOVA TRANSAZIONE");
        this.newCategoryButton.setOnAction(event -> this.showNewCategory());
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
        this.categoryChoice.setItems(this.controller.getCategories());
        this.categoryChoice.setConverter(super.createStringConverter(Category::getName));
        this.accountChoice.setItems(this.controller.getAccounts());
        this.accountChoice.setConverter(super.createStringConverter(Account::getName));
        this.repetitionChioce.setItems(this.controller.getRepetitions());
        this.repetitionChioce.setConverter(super.createStringConverter(Repetition::getName));
        this.repetitionChioce.setValue(Repetition.ONCE);
        this.typeChoice.setItems(FXCollections.observableArrayList("Entrata", "Uscita"));
        this.typeChoice.setValue("Uscita");
        this.dataPicker.setValue(LocalDate.now());
        this.hoursTextField.setText(StaticFormats.formatTwoDigits(LocalDateTime.now().getHourOfDay()));
        this.minutesTextField.setText(StaticFormats.formatTwoDigits(LocalDateTime.now().getMinuteOfHour()));
    }

    /**
     * Method that shows on the screen the window for creating a new category to add to the database.
     */
    private void showNewCategory() {
        final NewCategoryController controller = new NewCategoryControllerImpl(this.controller.getManager());
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) controller.getView().getRoot()));
        stage.show();
    }

    /**
     * {@inheritDoc}
     * If the required fields are filled in, create the transaction.
     */
    @Override
    public final void save() {
        if (this.descriptionTextField.getText().isEmpty() || FinanceWindow.isNotNumeric(this.amountTextField.getText())
                || this.categoryChoice.getValue() == null || this.accountChoice.getValue() == null
                || this.repetitionChioce.getValue() == null || Double.parseDouble(this.amountTextField.getText()) <= 0
                || this.hoursTextField.getText().isEmpty() || this.minutesTextField.getText().isEmpty()
                || this.typeChoice.getValue() == null || Double.parseDouble(this.amountTextField.getText()) * 100 % 1 != 0) {
            StaticAllerts.allert("I campi non sono stati compilati correttamente.");
        } else {
            try {
                this.controller.newTransaction(this.descriptionTextField.getText(),
                        Double.parseDouble(this.amountTextField.getText()) * ("Uscita".equals(this.typeChoice.getValue()) ? -1 : 1),
                        this.categoryChoice.getValue(), this.accountChoice.getValue(), this.dataPicker.getValue(),
                        Integer.parseInt(this.hoursTextField.getText()), Integer.parseInt(this.minutesTextField.getText()),
                        this.repetitionChioce.getValue());
            } catch (final UnsupportedOperationException e) {
                StaticAllerts.allert("Non posso eseguire una transazione in una data futura.");
            }
            this.close();
        }
    }
}
