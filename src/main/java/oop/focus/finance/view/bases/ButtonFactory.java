package oop.focus.finance.view.bases;

import oop.focus.finance.controller.ChangeViewController;
import oop.focus.finance.controller.TransactionsController;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.Transaction;

import java.util.function.Predicate;

/**
 * Interface that implements the creation of a generic finance button.
 */
public interface ButtonFactory {

    /**
     * @param name of the button
     * @param predicate filter the transactions we want to view
     * @param manager finance manager
     * @return a FinanceMenuButton that has as its action the visualization of filtered transactions
     */
    FinanceButton<ChangeViewController> getTransactions(String name,
                                                        Predicate<Transaction> predicate, FinanceManager manager);

    /**
     * @param name of the button
     * @param manager of finance
     * @return a FinanceMenuButton that has as its action the visualization of finance statistic
     */
    FinanceButton<ChangeViewController> getStatistics(String name, FinanceManager manager);

    /**
     * @param name of the button
     * @param manager finance manager
     * @return a FinanceMenuButton that has as its action the visualization of SubscriptionsView
     */
    FinanceButton<ChangeViewController> getSubscriptions(String name, FinanceManager manager);

    /**
     * @param name of the button
     * @param manager finance manager
     * @return a FinanceMenuButton that has as its action the visualization of GroupView
     */
    FinanceButton<ChangeViewController> getGroupTransactions(String name, FinanceManager manager);

    /**
     * @param account whose transactions we want to see
     * @return a FinanceMenuButton that has as its action the visualization of account's transactions
     */
    FinanceButton<TransactionsController> getAccountTransactions(Account account);

    /**
     * @return a FinanceMenuButton that has as its action the visualization of all transactions
     */
    FinanceButton<TransactionsController> getAllAccountTransactions();
}
