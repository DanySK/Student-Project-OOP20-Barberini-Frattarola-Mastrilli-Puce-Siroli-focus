package oop.focus.finance.view.windows;

import oop.focus.common.View;

public interface FinanceWindow extends View {

    /**
     * The window closes.
     */
    void close();

    /**
     * Saves the changes in the database and closes the window.
     */
    void save();

    /**
     * It shows a pop-up on the screen indicating
     * that some fields have not been filled in correctly.
     */
    void allert();
}
