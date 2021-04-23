package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.NewCategoryController;
import oop.focus.finance.view.StaticAlerts;
import oop.focus.finance.view.StaticFormats;

/**
 * Class that implements the view of creating a new category.
 */
public class NewCategoryViewImplImpl extends FinanceWindowImpl {

    @FXML
    private Label titleLabel, nameLabel, amountLabel, colorLabel, currencyLabel;
    @FXML
    private TextField nameTextField, amountTextField;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Button cancelButton, saveButton;

    private final NewCategoryController controller;

    public NewCategoryViewImplImpl(final NewCategoryController controller) {
        this.controller = controller;
        this.loadFXML(FXMLPaths.NEWACCOUNT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void populate() {
        this.titleLabel.setText("NUOVA CATEGORIA");
        this.amountLabel.setVisible(false);
        this.amountTextField.setVisible(false);
        this.currencyLabel.setVisible(false);
        this.cancelButton.setOnAction(event -> this.close());
        this.saveButton.setOnAction(event -> this.save());
    }

    /**
     * {@inheritDoc}
     * If the required fields are filled in, create the category.
     */
    @Override
    public final void save() {
        if (this.nameTextField.getText().isEmpty()) {
            StaticAlerts.alert("I campi non sono stati compilati correttamente.");
        } else {
            this.controller.newCategory(this.nameTextField.getText(), StaticFormats.formatColor(this.colorPicker.getValue()));
            this.close();
        }
    }
}
