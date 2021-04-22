package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oop.focus.finance.controller.FXMLPaths;

import oop.focus.finance.controller.NewAccountController;
import oop.focus.finance.view.StaticAllerts;
import oop.focus.finance.view.StaticFormats;

/**
 * Class that implements the view of creating a new account.
 */
public class NewAccountViewImpl extends GenericWindow {

    @FXML
    private Label titleLabel, nameLabel, amountLabel, colorLabel;
    @FXML
    private TextField nameTextfield, amountTextfield;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Button cancelButton, saveButton;

    private final NewAccountController controller;

    public NewAccountViewImpl(final NewAccountController controller) {
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
        if (this.nameTextfield.getText().isEmpty() || FinanceWindow.isNotNumeric(this.amountTextfield.getText())
                || Double.parseDouble(this.amountTextfield.getText()) * 100 % 100 != 0) {
            StaticAllerts.allert("I campi non sono stati compilati correttamente.");
        } else {
            this.controller.newAccount(this.nameTextfield.getText(), StaticFormats.formatColor(this.colorPicker.getValue()),
                        Double.parseDouble(this.amountTextfield.getText()));
            this.close();
        }
    }
}
