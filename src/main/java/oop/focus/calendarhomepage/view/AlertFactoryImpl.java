package oop.focus.calendarhomepage.view;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertFactoryImpl implements AlertFactory {
    private Alert alert;

    public AlertFactoryImpl() {
        this.alert = new Alert(AlertType.WARNING);
    }

    @Override
    public final Alert createIncompleteFieldAlert() {
        this.alert = new Alert(AlertType.ERROR);
        alert.setTitle(Constants.WARNING);
        alert.setHeaderText("I campi non sono stati riempiti correttamente!");
        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            alert.close();
        }
        return alert;
    }

    @Override
    public final Alert createImpossibleSaveElement() {
        this.alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(Constants.ERROR);
        alert.setHeaderText("Impossibile salvare questo elemento!");
        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            alert.close();
        }
        return alert;
    }

    @Override
    public final Alert createHourOrDateError() {
        this.alert = new Alert(AlertType.ERROR);
        alert.setTitle(Constants.WARNING);
        alert.setHeaderText("Sono stati inseriti orario o data non validi");
        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            alert.close();
        }
        return alert;
    }

    @Override
    public final Alert createAlreadyPresentItem() {
        this.alert = new Alert(AlertType.ERROR);
        alert.setTitle(Constants.WARNING);
        alert.setHeaderText("L'elemento inserito è già presente, inserirne un altro o tornare indietro!");
        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            alert.close();
        }
        return alert;
    }

    public final Alert createEventWarning() {
        this.alert = new Alert(AlertType.ERROR);
        alert.setTitle(Constants.WARNING);
        alert.setHeaderText("L'elemento inserito è già presente o è già presente un evento nell'orario e il giorno selezionato!");
        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            alert.close();
        }
        return alert;
    }

    private static class Constants {
        private static final String WARNING = "Attenzione!";
        private static final String ERROR = "Errore!";
    }
}
