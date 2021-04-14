package oop.focus.homepage.view;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class AllertGenerator {

    private final Alert alert;

    public AllertGenerator() {
        alert = new Alert(AlertType.ERROR);
    }

    public final void showAllert() {

        alert.setTitle("Error");
        alert.setContentText("Riempire correttamente i campi o tornare indietro");

        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    public final void checkFieldsFilled() {
        alert.setHeaderText("I campi non sono stati riempiti correttamente!");
    }

    public final void invalidEntry() {
        alert.setHeaderText("Hai inserito un orario o una data non valida");
    }

    public final void createWarningAllert(final int headerTextChoice) {
        switch (headerTextChoice) {
            case 1 :
                this.checkFieldsFilled();
                break;
            default :
                this.invalidEntry();
        }
        this.showAllert();
    }

}
