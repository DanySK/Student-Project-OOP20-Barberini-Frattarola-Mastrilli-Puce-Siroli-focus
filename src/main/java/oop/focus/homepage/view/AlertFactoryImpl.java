package oop.focus.homepage.view;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertFactoryImpl implements AlertFactory {

    @Override
    public final Alert createIncompleteFieldAlert() {
        final Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Attenzione!");
        alert.setHeaderText("I campi non sono stati riempiti correttamente!");
        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            alert.close();
        }
        return alert;
    }

    @Override
    public final Alert createImpossibleSaveElement() {
        final Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Errore!");
        alert.setHeaderText("Impossibile salvare questo elemento!");
        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            alert.close();
        }
        return alert;
    }

    @Override
    public final Alert createHourOrDateError() {
        final Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Attenzione!");
        alert.setHeaderText("Sono stati inseriti orario o data non validi");
        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            alert.close();
        }
        return alert;
    }
}
