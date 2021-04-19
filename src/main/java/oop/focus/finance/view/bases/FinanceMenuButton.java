package oop.focus.finance.view.bases;

import javafx.scene.control.Button;

/**
 * Interface that implements a finance button, consisting of a button and an action.
 *
 * @param <X> type of controller in which the method executed in the button action is present
 */
public interface FinanceMenuButton<X> {

    /**
     * @return the Button of FinanceMenuButton
     */
    Button getButton();

    /**
     * Returns the action to be performed on the controller when the button is clicked.
     *
     * @param controller that contains the method for the action
     */
    void action(X controller);
}
