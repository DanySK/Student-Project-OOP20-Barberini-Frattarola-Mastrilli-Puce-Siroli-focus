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

public class NewGroupTransactionViewImpl extends GenericWindow<NewGroupTransactionController> {

    private static final int MAX_HOURS = 23;
    private static final int MAX_MINUTES = 59;

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
    }

    @Override
    public final void save() {
        if (this.descriptionTextField.getText().isEmpty() || isNotNumeric(this.amountTextField.getText())
                || this.multiSelector.getSelected().size() == 0 || this.madeByChoice.getValue() == null
                || (!this.hoursTextField.getText().isEmpty() && (isNotNumeric(this.hoursTextField.getText())
                || Integer.parseInt(this.hoursTextField.getText()) < 0
                || Integer.parseInt(this.hoursTextField.getText()) > MAX_HOURS))
                || (!this.minutesTextField.getText().isEmpty() && (isNotNumeric(this.minutesTextField.getText())
                || (Integer.parseInt(this.hoursTextField.getText()) < 0
                || Integer.parseInt(this.hoursTextField.getText()) > MAX_MINUTES)))) {
            super.allert("I campi non sono stati compilati correttamente.");
        } else {
            super.getX().newGroupTransaction(this.descriptionTextField.getText(), this.madeByChoice.getValue(),
                    this.multiSelector.getSelected(), Double.parseDouble(this.amountTextField.getText()),
                    this.dataPicker.getValue(),
                    this.hoursTextField.getText().isEmpty() ? LocalDateTime.now().getHourOfDay()
                            : Integer.parseInt(this.hoursTextField.getText()),
                    this.minutesTextField.getText().isEmpty() ? LocalDateTime.now().getMinuteOfHour()
                            : Integer.parseInt(this.minutesTextField.getText()));
            this.close();
        }
    }

}
