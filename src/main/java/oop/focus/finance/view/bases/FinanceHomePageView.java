package oop.focus.finance.view.bases;

import oop.focus.common.View;

public interface FinanceHomePageView extends View {

    /**
     * Populate accountsScroll with all the accounts.
     */
    void populateAccounts();

    /**
     * Populate movementsScroll with last day transactions.
     */
    void populateRecentTransactions();

    /**
     * Populate financeHotKeyScroll with all the quick transactions.
     */
    void populateQuickTransactions();
}
