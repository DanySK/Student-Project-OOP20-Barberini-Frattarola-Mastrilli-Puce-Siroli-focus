package oop.focus.finance.view.bases;

import oop.focus.common.View;

/**
 * Interface that implements a generic view of finance.
 *
 * @param <X> type of the element passed as a field
 */
public interface FinanceView<X> extends View {

    /**
     * Populates elements of fxml file.
     */
    void populate();

    /**
     * Formats an amount in a formatted a string.
     *
     * @param amount in euro
     * @return a formatted version of amount
     */
    String format(double amount);
}
