package oop.focus.finance.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oop.focus.common.Repetition;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.FinanceHomePageController;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;
import oop.focus.homepage.view.AllertGenerator;
import org.joda.time.LocalDateTime;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class NewTransactionViewImpl implements Initializable, FinanceWindow {

    @FXML
    private Pane newTransactionPane;
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

    private final FinanceHomePageController controller;
    private Parent root;

    public NewTransactionViewImpl(final FinanceHomePageController controller) {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXMLPaths.NEWMOVEMENT.getPath()));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final Parent getRoot() {
        return this.root;
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        this.populate();
    }

    private void populate() {
        this.titleLabel.setText("Nuova transazione");
        this.descriptionLabel.setText("Descrizione:");
        this.amountLabel.setText("Importo:                E");
        this.categoryLabel.setText("Categoria:");
        this.repetitionLabel.setText("Ripetizione:");
        this.dateLabel.setText("Data:");
        this.accountLabel.setText("Conto:");
        this.cancelButton.setText("Cancella");
        this.saveButton.setText("Salva");
        this.categoryChoice.setItems(this.controller.getCategories());
        this.accountChoice.setItems(this.controller.getAccounts());
        this.repetitionChioce.setItems(this.controller.getRepetitions());
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
    }

    @Override
    public final void close() {
        final Stage stage = (Stage) this.newTransactionPane.getScene().getWindow();
        stage.close();
    }

    @Override
    public final void save() {
        if (this.descriptionTextField.getText().isEmpty() || !isNumeric(this.amountTextField.getText())
                || this.categoryChoice.getValue() == null || this.accountChoice.getValue() == null
                || this.repetitionChioce.getValue() == null
                || (this.dataPicker.getValue() != null && this.dataPicker.getValue().isAfter(LocalDate.now()))) {
            final AllertGenerator allert = new AllertGenerator();
            allert.showAllert();
        } else {
            this.controller.newTransaction(this.descriptionTextField.getText(),
                    Double.parseDouble(this.amountTextField.getText()), this.categoryChoice.getValue(),
                    this.accountChoice.getValue(), this.dataPicker.getValue() == null ? LocalDateTime.now()
                            : new LocalDateTime(this.dataPicker.getValue().getYear(), this.dataPicker.getValue().getMonthValue(),
                            this.dataPicker.getValue().getDayOfMonth(), 0, 0, 0),
                    this.repetitionChioce.getValue());
            this.close();
        }
    }

    private static boolean isNumeric(final String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
