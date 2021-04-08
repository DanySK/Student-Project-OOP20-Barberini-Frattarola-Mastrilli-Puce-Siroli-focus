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
}
