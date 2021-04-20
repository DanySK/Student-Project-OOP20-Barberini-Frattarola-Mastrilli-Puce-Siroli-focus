package oop.focus.finance.view.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import oop.focus.finance.controller.FXMLPaths;

import oop.focus.finance.controller.NewAccountController;

/**
 * Class that implements the view of creating a new account.
 */
public class NewAccountViewImpl extends GenericWindow<NewAccountController> {

    private static final int RGB_MAX_VALUE = 255;

    @FXML
    private Label titleLabel, nameLabel, amountLabel, colorLabel;
    @FXML
    private TextField nameTextfield, amountTextfield;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Button cancelButton, saveButton;

    public NewAccountViewImpl(final NewAccountController controller) {
        super(controller, FXMLPaths.NEWACCOUNT);
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
            super.allert("I campi non sono stati compilati correttamente.");
        } else {
            super.getX().newAccount(this.nameTextfield.getText(), toRGBCode(this.colorPicker.getValue()),
                        Double.parseDouble(this.amountTextfield.getText()));
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
