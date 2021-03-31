package oop.focus.finance.view;

import oop.focus.finance.controller.BaseController;

public interface ButtonFactory {

    /**
     * @param controller of finance
     * @return a FinanceMenuButton that has as its action the visualization of TransactionView
     */
    FinanceMenuButton getTransactions(BaseController controller);

    /**
     * @param controller of finance
     * @return a FinanceMenuButton that has as its action the visualization of OutingsView
     */
    FinanceMenuButton getOutings(BaseController controller);

    /**
     * @param controller of finance
     * @return a FinanceMenuButton that has as its action the visualization of IncomesView
     */
    FinanceMenuButton getIncomes(BaseController controller);

    /**
     * @param controller of finance
     * @return a FinanceMenuButton that has as its action the visualization of SubscriptionsView
     */
    FinanceMenuButton getSubscriptions(BaseController controller);

    /**
     * @param controller of finance
     * @return a FinanceMenuButton that has as its action the visualization of GroupView
     */
    FinanceMenuButton getGroupTransactions(BaseController controller);
}
