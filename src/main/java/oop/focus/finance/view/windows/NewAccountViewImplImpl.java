package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oop.focus.finance.controller.FXMLPaths;

import oop.focus.finance.controller.NewAccountController;
import oop.focus.finance.view.StaticAlerts;
import oop.focus.finance.view.StaticFormats;

/**
 * Class that implements the view of creating a new account.
 */
public class NewAccountViewImplImpl extends FinanceWindowImpl {

    @FXML
    private Label titleLabel, nameLabel, amountLabel, colorLabel;
    @FXML
    private TextField nameTextField, amountTextField;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Button cancelButton, saveButton;

    private final NewAccountController controller;

    public NewAccountViewImplImpl(final NewAccountController controller) {
        this.controller = controller;
        this.loadFXML(FXMLPaths.NEWACCOUNT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populate() {
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
    }

    /**
     * {@inheritDoc}
     * If the required fields are filled in, create the account.
     */
    @Override
    public final void save() {
        if (this.nameTextField.getText().isEmpty() || FinanceWindow.isNotNumeric(this.amountTextField.getText())
                || Double.parseDouble(this.amountTextField.getText()) * 100 % 100 != 0) {
            StaticAlerts.alert("I campi non sono stati compilati correttamente.");
        } else {
            this.controller.newAccount(this.nameTextField.getText(), StaticFormats.formatColor(this.colorPicker.getValue()),
                        Double.parseDouble(this.amountTextField.getText()));
            this.close();
        }
    }
}
