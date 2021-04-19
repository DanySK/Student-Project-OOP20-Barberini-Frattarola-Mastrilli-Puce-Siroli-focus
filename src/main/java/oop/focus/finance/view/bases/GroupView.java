package oop.focus.finance.view.bases;

import oop.focus.common.View;

/**
 * Interface that implements the group view.
 */
public interface GroupView extends View {

    /**
     * Show people in the groupMovementsScroll.
     *
     */
    void showPeople();

    /**
     * Show group transactions in the groupMovementsScroll.
     *
     */
    void showTransactions();
}
