package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.NewGroupTransactionController;
import oop.focus.calendar.persons.model.Person;
import oop.focus.finance.view.StaticAlerts;
import oop.focus.statistics.view.MultiSelectorView;
import org.joda.time.LocalDateTime;

import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * Class that implements the view of creating a new group transaction.
 */
public class NewGroupTransactionViewImpl extends FinanceWindowImpl {

    @FXML
    private Label titleLabel, descriptionLabel, madeByLabel, forLabel, amountLabel;
    @FXML
    private TextField descriptionTextField, amountTextField, hoursTextField, minutesTextField;
    @FXML
    private ComboBox<Person> madeByChoice;
    @FXML
    private VBox multiVBox;
    @FXML
    private Button cancelButton, saveButton;
    @FXML
    private DatePicker dataPicker;

    private MultiSelectorView<Person> multiSelector;
    private final NewGroupTransactionController controller;

    public NewGroupTransactionViewImpl(final NewGroupTransactionController controller) {
        this.controller = controller;
        this.loadFXML(FXMLPaths.NEWGROUPMOV);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populate() {
        this.madeByChoice.setItems(this.controller.getGroupList());
        this.madeByChoice.setConverter(super.createStringConverter(Person::getName));
        this.multiSelector = new MultiSelectorView<>(this.controller.getGroup(), Person::getName);
        this.multiVBox.getChildren().add(this.multiSelector.getRoot());
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
        this.dataPicker.setValue(LocalDate.now());
        this.hoursTextField.setText(new DecimalFormat("#00").format(LocalDateTime.now().getHourOfDay()));
        this.minutesTextField.setText(new DecimalFormat("#00").format(LocalDateTime.now().getMinuteOfHour()));
    }

    /**
     * {@inheritDoc}
     * If the required fields are filled in, create the group transaction.
     */
    @Override
    public final void save() {
        if (this.descriptionTextField.getText().isEmpty() || FinanceWindow.isNotNumeric(this.amountTextField.getText())
                || this.multiSelector.getSelected().size() == 0 || this.madeByChoice.getValue() == null
                || this.hoursTextField.getText().isEmpty() || Double.parseDouble(this.amountTextField.getText()) * 100 % 1 != 0
                || this.minutesTextField.getText().isEmpty() || Double.parseDouble(this.amountTextField.getText()) < 0) {
            StaticAlerts.alert("I campi non sono stati compilati correttamente.");
        } else if ((Double.parseDouble(this.amountTextField.getText()) * 100) % this.multiSelector.getSelected().size() > 0) {
            StaticAlerts.alert("Non e' possibile dividere correttamente l'importo tra le persone selezionate.");
        } else {
            this.controller.newGroupTransaction(this.descriptionTextField.getText(), this.madeByChoice.getValue(),
                    this.multiSelector.getSelected(), Double.parseDouble(this.amountTextField.getText()),
                    this.dataPicker.getValue(), Integer.parseInt(this.hoursTextField.getText()),
                    Integer.parseInt(this.minutesTextField.getText()));
            this.close();
        }
    }

}
