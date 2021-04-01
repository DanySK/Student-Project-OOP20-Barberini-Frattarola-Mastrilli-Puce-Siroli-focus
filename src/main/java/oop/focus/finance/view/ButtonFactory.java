package oop.focus.finance.view;

import oop.focus.db.DataSource;
import oop.focus.finance.controller.BaseController;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.model.Account;

public interface ButtonFactory {

    /**
     * @param controller of finance
     * @param db finance DataBase
     * @return a FinanceMenuButton that has as its action the visualization of TransactionView
     */
    FinanceMenuButton getTransactions(BaseController controller, DataSource db);

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

    /**
     * @param controller of finance
     * @param account whose transactions we want to see
     * @return a FinanceMenuButton that has as its action the visualization of account's transactions
     */
    AccountsMenuButton getAccountTransactions(TransactionsController controller, Account account);

    /**
     * @param controller of finance
     * @return a FinanceMenuButton that has as its action the visualization of every transactions
     */
    AccountsMenuButton getAccountTransactions(TransactionsController controller);
}
