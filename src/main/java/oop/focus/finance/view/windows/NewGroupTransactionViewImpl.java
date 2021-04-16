package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.NewGroupTransactionController;
import oop.focus.homepage.model.Person;
import oop.focus.statistics.view.MultiSelectorView;
import org.joda.time.LocalDateTime;

import java.time.LocalDate;

public class NewGroupTransactionViewImpl extends GenericWindow<NewGroupTransactionController> {

    @FXML
    private Label titleLabel, descriptionLabel, madeByLabel, forLabel, amountLabel;
    @FXML
    private TextField descriptionTextField, amountTextField, hoursTextField, minutesTextField;
    @FXML
    private ChoiceBox<Person> madeByChoice;
    @FXML
    private VBox multiVBox;
    @FXML
    private Button cancelButton, saveButton;
    @FXML
    private DatePicker dataPicker;

    private MultiSelectorView<Person> multiSelector;

    public NewGroupTransactionViewImpl(final NewGroupTransactionController controller) {
        super(controller, FXMLPaths.NEWGROUPMOV);
    }

    @Override
    public final void populate() {
        this.madeByChoice.setItems(super.getX().getGroupList());
        this.multiSelector = new MultiSelectorView<>(super.getX().getGroup(), Person::getName);
        this.multiVBox.getChildren().add(this.multiSelector.getRoot());
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
        this.dataPicker.setValue(LocalDate.now());
        this.hoursTextField.setText("" + LocalDateTime.now().getHourOfDay());
        this.minutesTextField.setText("" + LocalDateTime.now().getMinuteOfHour());
    }

    @Override
    public final void save() {
        if (this.descriptionTextField.getText().isEmpty() || isNotNumeric(this.amountTextField.getText())
                || this.multiSelector.getSelected().size() == 0 || this.madeByChoice.getValue() == null
                || this.hoursTextField.getText().isEmpty() || this.minutesTextField.getText().isEmpty()) {
            super.allert("I campi non sono stati compilati correttamente.");
        } else if ((Double.parseDouble(this.amountTextField.getText()) * 100) % this.multiSelector.getSelected().size() > 0) {
            super.allert("Non e' possibile dividere correttamente l'importo tra le persone selezionate.");
        } else {
            super.getX().newGroupTransaction(this.descriptionTextField.getText(), this.madeByChoice.getValue(),
                    this.multiSelector.getSelected(), Double.parseDouble(this.amountTextField.getText()),
                    this.dataPicker.getValue(), Integer.parseInt(this.hoursTextField.getText()),
                    Integer.parseInt(this.minutesTextField.getText()));
            this.close();
        }
    }

}
