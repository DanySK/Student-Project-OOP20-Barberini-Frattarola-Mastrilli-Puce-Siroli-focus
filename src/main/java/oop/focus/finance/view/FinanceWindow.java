package oop.focus.finance.view;

import oop.focus.common.View;

public interface FinanceWindow extends View {
    /**
     * The window closes.
     */
    void close();

    /**
     * Saves the data in the database and closes the window.
     */
    void save();
}
