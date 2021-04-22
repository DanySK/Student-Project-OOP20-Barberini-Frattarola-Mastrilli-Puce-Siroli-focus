package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.NewCategoryController;
import oop.focus.finance.view.StaticAllerts;
import oop.focus.finance.view.StaticFormats;

/**
 * Class that implements the view of creating a new category.
 */
public class NewCategoryViewImpl extends GenericWindow {

    @FXML
    private Label titleLabel, nameLabel, amountLabel, colorLabel, currencyLabel;
    @FXML
    private TextField nameTextfield, amountTextfield;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Button cancelButton, saveButton;

    private final NewCategoryController controller;

    public NewCategoryViewImpl(final NewCategoryController controller) {
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
        this.amountTextfield.setVisible(false);
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
        if (this.nameTextfield.getText().isEmpty()) {
            StaticAllerts.allert("I campi non sono stati compilati correttamente.");
        } else {
            this.controller.newCategory(this.nameTextfield.getText(), StaticFormats.formatColor(this.colorPicker.getValue()));
            this.close();
        }
    }
}
