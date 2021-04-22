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
        this.alert.setTitle(Constants.WARNING);
        this.alert.setHeaderText("I campi non sono stati riempiti correttamente!");
        final Optional<ButtonType> result = this.alert.showAndWait();
        if (result.isEmpty() || result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            this.alert.close();
        }
        return this.alert;
    }

    @Override
    public final Alert createImpossibleSaveElement() {
        this.alert = new Alert(AlertType.CONFIRMATION);
        this.alert.setTitle(Constants.ERROR);
        this.alert.setHeaderText("Impossibile salvare questo elemento!");
        final Optional<ButtonType> result = this.alert.showAndWait();
        if (result.isEmpty() || result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            this.alert.close();
        }
        return this.alert;
    }

    @Override
    public final Alert createHourOrDateError() {
        this.alert = new Alert(AlertType.ERROR);
        this.alert.setTitle(Constants.WARNING);
        this.alert.setHeaderText("Sono stati inseriti orario o data non validi");
        final Optional<ButtonType> result = this.alert.showAndWait();
        if (result.isEmpty() || result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            this.alert.close();
        }
        return this.alert;
    }

    @Override
    public final Alert createAlreadyPresentItem() {
        this.alert = new Alert(AlertType.ERROR);
        this.alert.setTitle(Constants.WARNING);
        this.alert.setHeaderText("L'elemento inserito è già presente, inserirne un altro o tornare indietro!");
        final Optional<ButtonType> result = this.alert.showAndWait();
        if (result.isEmpty() || result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            this.alert.close();
        }
        return this.alert;
    }

    public final Alert createEventWarning() {
        this.alert = new Alert(AlertType.ERROR);
        this.alert.setTitle(Constants.WARNING);
        this.alert.setHeaderText("L'elemento inserito è già presente o è già presente un evento nell'orario e il giorno selezionato!");
        final Optional<ButtonType> result = this.alert.showAndWait();
        if (result.isEmpty() || result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            this.alert.close();
        }
        return this.alert;
    }

    private static class Constants {
        private static final String WARNING = "Attenzione!";
        private static final String ERROR = "Errore!";
    }
}
