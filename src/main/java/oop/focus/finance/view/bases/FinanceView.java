package oop.focus.finance.view.bases;

import javafx.scene.control.ButtonType;
import oop.focus.common.View;

import java.util.Optional;

public interface FinanceView<X> extends View {

    /**
     * @return the object to which the view refers
     */
    X getX();

    /**
     * Populates elements of fxml file.
     */
    void populate();

    /**
     * Show a pop-up indicating an error.
     *
     * @param message to diplay
     */
    void allert(String message);

    /**
     * Show a pop-up asking for confirmation.
     *
     * @param message to display
     * @return the result
     */
    Optional<ButtonType> confirm(String message);

    /**
     * Formats an amount in a formatted a string.
     *
     * @param amount in euro
     * @return a formatted version of amount
     */
    String format(double amount);
}
