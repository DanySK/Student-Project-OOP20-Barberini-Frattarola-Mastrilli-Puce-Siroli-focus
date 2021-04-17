package oop.focus.finance.view.windows;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
import org.joda.time.LocalDateTime;

import java.time.LocalDate;

public class NewTransactionViewImpl extends GenericWindow<NewTransactionController> {

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
    private ChoiceBox<String> typeChoice;
    @FXML
    private Button cancelButton, saveButton, newCategoryButton;

    public NewTransactionViewImpl(final NewTransactionController controller) {
        super(controller, FXMLPaths.NEWMOVEMENT);
    }

    @Override
    public final void populate() {
        this.newCategoryButton.setOnAction(event -> this.showNewCategory());
        this.titleLabel.setText("NUOVA TRANSAZIONE");
        this.categoryChoice.setItems(super.getX().getCategories());
        this.accountChoice.setItems(super.getX().getAccounts());
        this.repetitionChioce.setItems(super.getX().getRepetitions());
        this.typeChoice.setItems(FXCollections.observableArrayList("Entrata", "Uscita"));
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
        this.dataPicker.setValue(LocalDate.now());
        this.hoursTextField.setText("" + LocalDateTime.now().getHourOfDay());
        this.minutesTextField.setText("" + LocalDateTime.now().getMinuteOfHour());
        this.repetitionChioce.setValue(Repetition.ONCE);
        this.typeChoice.setValue("Uscita");
    }

    private void showNewCategory() {
        final NewCategoryController controller = new NewCategoryControllerImpl(super.getX().getManager());
        final Stage stage = new Stage();
        stage.setScene(new Scene((Parent) controller.getView().getRoot()));
        stage.show();
    }

    @Override
    public final void save() {
        if (this.descriptionTextField.getText().isEmpty() || isNotNumeric(this.amountTextField.getText())
                || this.categoryChoice.getValue() == null || this.accountChoice.getValue() == null
                || this.repetitionChioce.getValue() == null || Double.parseDouble(this.amountTextField.getText()) <= 0
                || this.hoursTextField.getText().isEmpty() || this.minutesTextField.getText().isEmpty()
                || this.typeChoice.getValue() == null) {
            super.allert("I campi non sono stati compilati correttamente.");
        } else {
            try {
                super.getX().newTransaction(this.descriptionTextField.getText(),
                        Double.parseDouble(this.amountTextField.getText()) * (this.typeChoice.getValue().equals("Uscita") ? -1 : 1),
                        this.categoryChoice.getValue(), this.accountChoice.getValue(), this.dataPicker.getValue(),
                        Integer.parseInt(this.hoursTextField.getText()), Integer.parseInt(this.minutesTextField.getText()),
                        this.repetitionChioce.getValue());
            } catch (UnsupportedOperationException e) {
                super.allert("Non posso eseguire una transazione in una data futura.");
            }
            this.close();
        }
    }
}
