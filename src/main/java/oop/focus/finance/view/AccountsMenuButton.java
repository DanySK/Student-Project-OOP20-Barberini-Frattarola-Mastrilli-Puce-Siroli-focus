package oop.focus.finance.view;

import javafx.scene.control.Button;
import oop.focus.finance.controller.TransactionsController;

public interface AccountsMenuButton {

    /**
     * @return the Button of AccountsMenuButton
     */
    Button getButton();

    /**
     * Returns the action to be performed on the controller when the button is clicked.
     *
     * @param controller that contains the method for the action
     */
    void getAction(TransactionsController controller);
}
