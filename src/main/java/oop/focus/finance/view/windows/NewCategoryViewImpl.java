package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import oop.focus.finance.controller.FXMLPaths;
import oop.focus.finance.controller.NewCategoryController;

/**
 * Class that implements the view of creating a new category.
 */
public class NewCategoryViewImpl extends GenericWindow<NewCategoryController> {

    private static final int RGB_MAX_VALUE = 255;

    @FXML
    private Label titleLabel, nameLabel, amountLabel, colorLabel, currencyLabel;
    @FXML
    private TextField nameTextfield, amountTextfield;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Button cancelButton, saveButton;

    public NewCategoryViewImpl(final NewCategoryController controller) {
        super(controller, FXMLPaths.NEWACCOUNT);
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
            super.allert("I campi non sono stati compilati correttamente.");
        } else {
            super.getX().newCategory(this.nameTextfield.getText(), toRGBCode(this.colorPicker.getValue()));
            this.close();
        }
    }

    /**
     * @param color of which we want the RGB code
     * @return RGB code og the color
     */
    public static String toRGBCode(final Color color) {
        return String.format("%02X%02X%02X", (int) (color.getRed() * RGB_MAX_VALUE),
                (int) (color.getGreen() * RGB_MAX_VALUE), (int) (color.getBlue() * RGB_MAX_VALUE));
    }
}
