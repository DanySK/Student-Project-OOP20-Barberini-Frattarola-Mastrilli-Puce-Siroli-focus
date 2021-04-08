package oop.focus.finance.view.bases;

import javafx.scene.control.Button;

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
    void getAction(X controller);
}
