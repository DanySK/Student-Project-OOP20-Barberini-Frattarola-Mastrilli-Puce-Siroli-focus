package oop.focus.homepage.view;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertFactoryImpl implements AlertFactory {

    private Alert alert;

    public final Alert createWarningAlert() {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Warning");

        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            alert.close();
        }
        return this.alert;
    }

    public final Alert createConfirmationAlert() {
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Sei sicuro di volere eiminare questo elemento ?");

        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            alert.close();
        }
        return this.alert;
    }
}
