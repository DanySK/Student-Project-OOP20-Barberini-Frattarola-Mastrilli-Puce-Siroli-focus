package oop.focus.finance.view;

import javafx.scene.control.Button;
import oop.focus.finance.controller.BaseController;

public interface FinanceMenuButton {

    /**
     * @return the Button of FinanceMenuButton
     */
    Button getButton();

    /**
     *
     *
     * @param controller
     */
    void getAction(BaseController controller);

}
