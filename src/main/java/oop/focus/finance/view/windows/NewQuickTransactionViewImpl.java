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
import oop.focus.finance.controller.NewQuickTransactionController;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;
import oop.focus.finance.view.StaticAlerts;

/**
 * Class that implements the view of creating a new quick transaction.
 */
public class NewQuickTransactionViewImpl extends FinanceWindowImpl {

    @FXML
    private Label titleLabel, repetitionLabel, dateLabel, divideLabel;
    @FXML
    private TextField descriptionTextField, amountTextField, hoursTextField, minutesTextField;
    @FXML
    private DatePicker dataPicker;
    @FXML
    private ComboBox<Category> categoryChoice;
    @FXML
    private ComboBox<Account> accountChoice;
    @FXML
    private ComboBox<Repetition> repetitionChoice;
    @FXML
    private ChoiceBox<String> typeChoice;
    @FXML
    private Button cancelButton, saveButton, newCategoryButton;

    private final NewQuickTransactionController controller;

    public NewQuickTransactionViewImpl(final NewQuickTransactionController controller) {
        this.controller = controller;
        this.loadFXML(FXMLPaths.NEWMOVEMENT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populate() {
        this.titleLabel.setText("NUOVA TRANSAZIONE RAPIDA");
        this.dateLabel.setVisible(false);
        this.dataPicker.setVisible(false);
        this.repetitionLabel.setVisible(false);
        this.repetitionChoice.setVisible(false);
        this.hoursTextField.setVisible(false);
        this.minutesTextField.setVisible(false);
        this.divideLabel.setVisible(false);
        this.categoryChoice.setItems(this.controller.getCategories());
        this.categoryChoice.setConverter(super.createStringConverter(Category::getName));
        this.accountChoice.setItems(this.controller.getAccounts());
        this.accountChoice.setConverter(super.createStringConverter(Account::getName));
        this.typeChoice.setItems(FXCollections.observableArrayList("Entrata", "Uscita"));
        this.typeChoice.setValue("Uscita");
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
        this.newCategoryButton.setOnAction(event -> this.showNewCategory());
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
     * If the required fields are filled in, create the quick transaction.
     */
    @Override
    public final void save() {
        if (this.descriptionTextField.getText().isEmpty() || FinanceWindow.isNotNumeric(this.amountTextField.getText())
                || this.categoryChoice.getValue() == null || this.accountChoice.getValue() == null
                || this.typeChoice.getValue() == null || Double.parseDouble(this.amountTextField.getText()) <= 0
                || Double.parseDouble(this.amountTextField.getText()) * 100 % 1 != 0) {
            StaticAlerts.alert("I campi non sono stati compilati correttamente.");
        } else {
            this.controller.newQuickTransaction(this.descriptionTextField.getText(),
                    Double.parseDouble(this.amountTextField.getText()) * ("Uscita".equals(this.typeChoice.getValue()) ? -1 : 1),
                    this.categoryChoice.getValue(), this.accountChoice.getValue());
            this.close();
        }
    }
}
