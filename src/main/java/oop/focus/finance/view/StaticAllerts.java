package oop.focus.finance.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public final class StaticAllerts {

    private StaticAllerts() {
    }

    /**
     * Show a pop-up indicating an error.
     *
     * @param message to diplay
     */
    public static void allert(final String message) {
        final Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(message);
        alert.setContentText("Premi ok per tornare indietro.");

        final Optional<ButtonType> result = alert.showAndWait();
        if (result.isEmpty() || result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    /**
     * Show a pop-up asking for confirmation.
     *
     * @param message to display
     * @return the result
     */
    public static Optional<ButtonType> confirm(final String message) {
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma");
        alert.setHeaderText(message);
        alert.setContentText("Premi ok per confermare.");
        return alert.showAndWait();
    }
}
